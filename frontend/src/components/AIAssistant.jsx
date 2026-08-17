import { useEffect, useRef, useState } from "react";
import {
    Button,
    Form
} from "react-bootstrap";

import ReactMarkdown from "react-markdown";
import remarkGfm from "remark-gfm";

import {
    createAISession,
    sendAIMessage,
    deleteAISession
} from "../services/api";

export default function AIAssistant({ onClose }) {

    const [sessionId, setSessionId] = useState(null);
    const [messages, setMessages] = useState([]);
    const [input, setInput] = useState("");
    const [loading, setLoading] = useState(false);
    const [closing, setClosing] = useState(false);

    const bottomRef = useRef(null);

    // =========================
    // START AI SESSION
    // =========================

    useEffect(() => {
        startSession();
    }, []);

    // =========================
    // AUTO SCROLL
    // =========================

    useEffect(() => {
        bottomRef.current?.scrollIntoView({
            behavior: "smooth"
        });
    }, [messages, loading]);

    // =========================
    // CREATE SESSION
    // =========================

    const startSession = async () => {

        try {

            const data = await createAISession();

            console.log("AI session created:", data);

            setSessionId(data.id);

        } catch (error) {

            console.error(
                "Failed to create AI session:",
                error
            );

        }
    };

    // =========================
    // SEND MESSAGE
    // =========================

    const sendMessage = async () => {

        if (
            !input.trim() ||
            !sessionId ||
            loading
        ) {
            return;
        }

        const text = input.trim();

        // Clear input
        setInput("");

        // Add user message immediately
        setMessages(prev => [
            ...prev,
            {
                role: "USER",
                content: text
            }
        ]);

        setLoading(true);

        try {

            console.log(
                "Sending message to session:",
                sessionId
            );

            const data = await sendAIMessage(
                sessionId,
                text
            );

            console.log(
                "AI response:",
                data
            );

            setMessages(prev => [
                ...prev,
                {
                    role: "ASSISTANT",
                    content: data.message
                }
            ]);

        } catch (error) {

            console.error(
                "AI message failed:",
                error
            );

            setMessages(prev => [
                ...prev,
                {
                    role: "ASSISTANT",
                    content:
                        "Something went wrong. Please try again."
                }
            ]);

        } finally {

            setLoading(false);

        }
    };

    // =========================
    // CLOSE ASSISTANT
    // =========================

    const close = async () => {

        if (closing) {
            return;
        }

        setClosing(true);

        const currentSessionId = sessionId;

        // Close UI immediately
        onClose();

        // Delete session in background
        if (currentSessionId) {

            try {

                console.log(
                    "Deleting AI session:",
                    currentSessionId
                );

                await deleteAISession(
                    currentSessionId
                );

                console.log(
                    "AI session deleted successfully"
                );

            } catch (error) {

                console.error(
                    "Failed to delete AI session:",
                    error
                );

            }

        }
    };

    // =========================
    // HANDLE SUGGESTION
    // =========================

    const useSuggestion = (text) => {

        setInput(text);

    };

    // =========================
    // UI
    // =========================

    return (
        <div className="ai-overlay">

            <div className="ai-panel">

                {/* =========================
                    HEADER
                ========================= */}

                <div className="ai-panel-header">

                    <div>

                        <div className="ai-title">
                            ✨ AI Assistant
                        </div>

                        <div className="ai-status">
                            Shopping assistant
                        </div>

                    </div>

                    <Button
                        type="button"
                        className="ai-close"
                        onClick={close}
                        disabled={closing}
                    >
                        ×
                    </Button>

                </div>

                {/* =========================
                    CHAT
                ========================= */}

                <div className="ai-chat">

                    {/* EMPTY STATE */}

                    {messages.length === 0 && (

                        <div className="ai-empty">

                            <div className="ai-big-icon">
                                ✨
                            </div>

                            <h3>
                                What can I help you find?
                            </h3>

                            <p>
                                Tell me your budget,
                                requirements or what
                                you're shopping for.
                            </p>

                            <div className="ai-suggestions">

                                <button
                                    type="button"
                                    onClick={() =>
                                        useSuggestion(
                                            "Suggest a laptop for coding under ₹70000"
                                        )
                                    }
                                >
                                    💻 Laptop for coding
                                </button>

                                <button
                                    type="button"
                                    onClick={() =>
                                        useSuggestion(
                                            "Suggest headphones under ₹5000"
                                        )
                                    }
                                >
                                    🎧 Headphones
                                </button>

                                <button
                                    type="button"
                                    onClick={() =>
                                        useSuggestion(
                                            "Suggest running shoes"
                                        )
                                    }
                                >
                                    👟 Running shoes
                                </button>

                            </div>

                        </div>

                    )}

                    {/* =========================
                        MESSAGES
                    ========================= */}

                    {messages.map(
                        (message, index) => (

                            <div
                                key={index}
                                className={
                                    message.role === "USER"
                                        ? "ai-message user"
                                        : "ai-message"
                                }
                            >

                                {/* AVATAR */}

                                <div className="ai-avatar">

                                    {message.role === "USER"
                                        ? "U"
                                        : "AI"}

                                </div>

                                {/* MESSAGE */}

                                <div className="ai-bubble">

                                    {message.role === "ASSISTANT" ? (

                                        <ReactMarkdown
                                            remarkPlugins={[
                                                remarkGfm
                                            ]}
                                        >
                                            {message.content}
                                        </ReactMarkdown>

                                    ) : (

                                        message.content

                                    )}

                                </div>

                            </div>

                        )
                    )}

                    {/* =========================
                        TYPING INDICATOR
                    ========================= */}

                    {loading && (

                        <div className="ai-message">

                            <div className="ai-avatar">
                                AI
                            </div>

                            <div className="ai-bubble typing">

                                <span />
                                <span />
                                <span />

                            </div>

                        </div>

                    )}

                    <div ref={bottomRef} />

                </div>

                {/* =========================
                    INPUT
                ========================= */}

                <div className="ai-input">

                    <Form.Control
                        as="textarea"
                        rows={1}
                        placeholder="Ask about products..."
                        value={input}
                        onChange={e =>
                            setInput(e.target.value)
                        }
                        onKeyDown={e => {

                            if (
                                e.key === "Enter" &&
                                !e.shiftKey
                            ) {

                                e.preventDefault();

                                sendMessage();

                            }

                        }}
                    />

                    <Button
                        type="button"
                        className="primary-button"
                        onClick={sendMessage}
                        disabled={
                            !input.trim() ||
                            loading ||
                            !sessionId
                        }
                    >
                        ↑
                    </Button>

                </div>

            </div>

        </div>
    );
}