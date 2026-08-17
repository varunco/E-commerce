package com.example.productcatalog.controller;

import com.example.productcatalog.model.SuggestionMessage;
import com.example.productcatalog.model.SuggestionSession;
import com.example.productcatalog.service.AIService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/session")
    public SuggestionSession createSession(
            Authentication authentication) {

        return aiService.createSession(
                authentication.getName()
        );
    }

    @PostMapping("/session/{id}")
    public Map<String, String> chat(
            @PathVariable Long id,
            @RequestBody Map<String, String> request,
            Authentication authentication) {

        String answer = aiService.chat(
                id,
                authentication.getName(),
                request.get("message")
        );

        return Map.of("message", answer);
    }

    @GetMapping("/session/{id}")
    public List<SuggestionMessage> messages(
            @PathVariable Long id) {

        return aiService.messages(id);
    }

    @DeleteMapping("/session/{id}")
    public String delete(@PathVariable Long id) {

        aiService.delete(id);

        return "Session deleted";
    }
}