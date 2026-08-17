import { useState } from "react";
import { Container, Form, Button, Card } from "react-bootstrap";
import { signup } from "../services/api";

export default function Signup() {

    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const handleSubmit = async e => {

        e.preventDefault();

        setError("");
        setLoading(true);

        try {

            await signup({
                username,
                email,
                password
            });

            window.location.href = "/login";

        } catch {
            setError(
                "Could not create account. Email may already exist."
            );
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="auth-page">

            <Container className="auth-container">

                <Card className="auth-card">

                    <div className="auth-logo">
                        N
                    </div>

                    <h1>Create account</h1>

                    <p className="auth-subtitle">
                        Join NexusStore today.
                    </p>

                    {error && (
                        <div className="error-box">
                            {error}
                        </div>
                    )}

                    <Form onSubmit={handleSubmit}>

                        <Form.Group className="mb-3">
                            <Form.Label>Username</Form.Label>

                            <Form.Control
                                value={username}
                                onChange={e =>
                                    setUsername(e.target.value)
                                }
                                placeholder="Your name"
                                required
                            />
                        </Form.Group>

                        <Form.Group className="mb-3">
                            <Form.Label>Email</Form.Label>

                            <Form.Control
                                type="email"
                                value={email}
                                onChange={e =>
                                    setEmail(e.target.value)
                                }
                                placeholder="you@example.com"
                                required
                            />
                        </Form.Group>

                        <Form.Group className="mb-4">
                            <Form.Label>Password</Form.Label>

                            <Form.Control
                                type="password"
                                value={password}
                                onChange={e =>
                                    setPassword(e.target.value)
                                }
                                placeholder="Create a password"
                                minLength={6}
                                required
                            />
                        </Form.Group>

                        <Button
                            type="submit"
                            className="primary-button w-100"
                            disabled={loading}
                        >
                            {loading
                                ? "Creating..."
                                : "Create Account"}
                        </Button>

                    </Form>

                    <div className="auth-footer">
                        Already have an account?
                        <a href="/login"> Sign in</a>
                    </div>

                </Card>

            </Container>

        </div>
    );
}