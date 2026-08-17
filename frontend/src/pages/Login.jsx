import { useState } from "react";
import { Container, Form, Button, Card } from "react-bootstrap";
import { login } from "../services/api";

export default function Login() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const handleSubmit = async e => {

        e.preventDefault();

        setError("");
        setLoading(true);

        try {

            const data = await login({
                email,
                password
            });

            localStorage.setItem(
                "token",
                data.token
            );

            window.location.href = "/";

        } catch {
            setError("Invalid email or password.");
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

                    <h1>Welcome back</h1>

                    <p className="auth-subtitle">
                        Sign in to continue shopping.
                    </p>

                    {error && (
                        <div className="error-box">
                            {error}
                        </div>
                    )}

                    <Form onSubmit={handleSubmit}>

                        <Form.Group className="mb-3">
                            <Form.Label>Email</Form.Label>

                            <Form.Control
                                type="email"
                                placeholder="you@example.com"
                                value={email}
                                onChange={e =>
                                    setEmail(e.target.value)
                                }
                                required
                            />
                        </Form.Group>

                        <Form.Group className="mb-4">
                            <Form.Label>Password</Form.Label>

                            <Form.Control
                                type="password"
                                placeholder="••••••••"
                                value={password}
                                onChange={e =>
                                    setPassword(e.target.value)
                                }
                                required
                            />
                        </Form.Group>

                        <Button
                            type="submit"
                            className="primary-button w-100"
                            disabled={loading}
                        >
                            {loading
                                ? "Signing in..."
                                : "Sign In"}
                        </Button>

                    </Form>

                    <div className="auth-footer">
                        Don't have an account?
                        <a href="/signup"> Create one</a>
                    </div>

                </Card>

            </Container>

        </div>
    );
}