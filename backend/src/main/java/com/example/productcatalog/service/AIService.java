package com.example.productcatalog.service;

import com.example.productcatalog.model.SuggestionMessage;
import com.example.productcatalog.model.SuggestionSession;
import com.example.productcatalog.model.User;
import com.example.productcatalog.repository.ProductRepository;
import com.example.productcatalog.repository.SuggestionSessionRepository;
import com.example.productcatalog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class AIService {

    private final SuggestionSessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Value("${groq.api.key}")
    private String apiKey;

    public AIService(
            SuggestionSessionRepository sessionRepository,
            UserRepository userRepository,
            ProductRepository productRepository) {

        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // =========================
    // CREATE AI SESSION
    // =========================

    public SuggestionSession createSession(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        SuggestionSession session = new SuggestionSession();

        session.setUser(user);

        return sessionRepository.save(session);
    }

    // =========================
    // CHAT WITH AI
    // =========================

    public String chat(
            Long sessionId,
            String email,
            String message) {

        // Find authenticated user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // Make sure session belongs to this user
        SuggestionSession session =
                sessionRepository.findByIdAndUserId(
                        sessionId,
                        user.getId()
                ).orElseThrow(() ->
                        new RuntimeException("Session not found"));

        // =========================
        // SAVE USER MESSAGE
        // =========================

        SuggestionMessage userMessage =
                new SuggestionMessage();

        userMessage.setSession(session);
        userMessage.setRole("USER");
        userMessage.setContent(message);

        session.getMessages().add(userMessage);

        // =========================
        // BUILD PRODUCT CATALOG
        // =========================

        String products = productRepository.findAll()
                .stream()
                .map(product ->
                        product.getName()
                                + " | ₹"
                                + product.getPrice()
                                + " | "
                                + product.getCategory().getName()
                                + " | "
                                + product.getDescription()
                )
                .reduce(
                        "",
                        (a, b) -> a + "\n" + b
                );

        // =========================
        // BUILD CHAT HISTORY
        // =========================

        String history = session.getMessages()
                .stream()
                .map(messageItem ->
                        messageItem.getRole()
                                + ": "
                                + messageItem.getContent()
                )
                .reduce(
                        "",
                        (a, b) -> a + "\n" + b
                );

        // =========================
        // SYSTEM PROMPT
        // =========================

        String systemPrompt = """
                You are an e-commerce shopping assistant.

                Your job is to help users choose products from the
                provided product catalog.

                IMPORTANT RULES:

                1. Recommend ONLY products that exist in the catalog.
                2. Never invent products.
                3. Consider the user's requirements and budget.
                4. Mention the product name and price when recommending.
                5. Explain recommendations briefly and clearly.
                6. If no suitable product exists, say so honestly.
                7. Keep your response concise and useful.

                PRODUCT CATALOG:
                %s

                CONVERSATION HISTORY:
                %s
                """.formatted(
                products,
                history
        );

        // =========================
        // DEBUG INFORMATION
        // =========================

        System.out.println("===== AI REQUEST =====");
        System.out.println("Session: " + sessionId);
        System.out.println("User: " + email);
        System.out.println("Message: " + message);

        System.out.println(
                "Groq API key configured: "
                        + (apiKey != null && !apiKey.isBlank())
        );

        // =========================
        // CREATE VALID JSON REQUEST
        // =========================

        String requestBody = """
                {
                  "model": "openai/gpt-oss-120b",
                  "messages": [
                    {
                      "role": "system",
                      "content": "%s"
                    },
                    {
                      "role": "user",
                      "content": "%s"
                    }
                  ]
                }
                """.formatted(
                jsonEscape(systemPrompt),
                jsonEscape(message)
        );

        System.out.println("===== GROQ REQUEST BODY =====");
        System.out.println(requestBody);

        // =========================
        // CALL GROQ
        // =========================

        String response;

        try {

            response = RestClient.create()
                    .post()
                    .uri(
                            "https://api.groq.com/openai/v1/chat/completions"
                    )
                    .header(
                            "Authorization",
                            "Bearer " + apiKey
                    )
                    .header(
                            "Content-Type",
                            "application/json"
                    )
                    .body(requestBody)
                    .retrieve()
                    .body(String.class);

        } catch (Exception e) {

            System.out.println("===== GROQ ERROR =====");

            e.printStackTrace();

            throw new RuntimeException(
                    "Failed to communicate with Groq AI",
                    e
            );
        }

        // =========================
        // GROQ RESPONSE
        // =========================

        System.out.println("===== GROQ RESPONSE =====");
        System.out.println(response);

        // =========================
        // EXTRACT AI ANSWER
        // =========================

        String answer = extractAnswer(response);

        // =========================
        // SAVE AI MESSAGE
        // =========================

        SuggestionMessage aiMessage =
                new SuggestionMessage();

        aiMessage.setSession(session);
        aiMessage.setRole("ASSISTANT");
        aiMessage.setContent(answer);

        session.getMessages().add(aiMessage);

        sessionRepository.save(session);

        return answer;
    }

    // =========================
    // GET CHAT HISTORY
    // =========================

    public List<SuggestionMessage> messages(
            Long sessionId) {

        return sessionRepository.findById(sessionId)
                .orElseThrow(() ->
                        new RuntimeException("Session not found"))
                .getMessages();
    }

    // =========================
    // DELETE SESSION
    // =========================

    public void delete(Long sessionId) {

        sessionRepository.deleteById(sessionId);
    }

    // =========================
    // JSON ESCAPE
    // =========================

    private String jsonEscape(String text) {

        return text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    // =========================
    // EXTRACT AI RESPONSE
    // =========================

    private String extractAnswer(String response) {

        String marker = "\"content\":\"";

        int start = response.indexOf(marker);

        if (start == -1) {
            return response;
        }

        start += marker.length();

        StringBuilder answer =
                new StringBuilder();

        boolean escaped = false;

        for (int i = start;
             i < response.length();
             i++) {

            char c = response.charAt(i);

            if (escaped) {

                if (c == 'n') {

                    answer.append('\n');

                } else if (c == 'r') {

                    answer.append('\r');

                } else if (c == 't') {

                    answer.append('\t');

                } else if (c == '"') {

                    answer.append('"');

                } else if (c == '\\') {

                    answer.append('\\');

                } else {

                    answer.append(c);
                }

                escaped = false;

            } else if (c == '\\') {

                escaped = true;

            } else if (c == '"') {

                break;

            } else {

                answer.append(c);
            }
        }

        return answer.toString();
    }
}