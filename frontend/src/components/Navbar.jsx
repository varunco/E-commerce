import { Container, Navbar, Nav, Button } from "react-bootstrap";

export default function Navigation({ onAI }) {

    const token = localStorage.getItem("token");

    const logout = () => {
        localStorage.removeItem("token");
        window.location.href = "/login";
    };

    return (
        <Navbar
            expand="lg"
            className="app-navbar"
            variant="dark"
        >
            <Container>

                <Navbar.Brand href="/">
                    <span className="brand-mark">N</span>
                    NexusStore
                </Navbar.Brand>

                <Navbar.Toggle />

                <Navbar.Collapse>

                    <Nav className="me-auto">
                        <Nav.Link href="/">
                            Products
                        </Nav.Link>

                        <Nav.Link href="#categories">
                            Categories
                        </Nav.Link>
                    </Nav>

                    <div className="d-flex gap-2">

                        {token ? (
                            <>
                                <Button
                                    className="ai-button"
                                    onClick={onAI}
                                >
                                    ✨ AI Assistant
                                </Button>

                                <Button
                                    variant="outline-light"
                                    onClick={logout}
                                >
                                    Logout
                                </Button>
                            </>
                        ) : (
                            <>
                                <Button
                                    variant="outline-light"
                                    href="/login"
                                >
                                    Login
                                </Button>

                                <Button
                                    className="primary-button"
                                    href="/signup"
                                >
                                    Sign Up
                                </Button>
                            </>
                        )}

                    </div>

                </Navbar.Collapse>

            </Container>
        </Navbar>
    );
}