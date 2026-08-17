import { useState } from "react";

import Navigation from "./components/Navbar";
import AIAssistant from "./components/AIAssistant";

import Products from "./pages/Products";
import Login from "./pages/Login";
import Signup from "./pages/Signup";

export default function App() {

    const [showAI, setShowAI] = useState(false);

    const path = window.location.pathname;

    if (path === "/login") {
        return <Login />;
    }

    if (path === "/signup") {
        return <Signup />;
    }

    const token = localStorage.getItem("token");

    return (
        <>
            <Navigation
                onAI={() => setShowAI(true)}
            />

            <Products />

            {showAI && (
                <AIAssistant
                    onClose={() =>
                        setShowAI(false)
                    }
                />
            )}
        </>
    );
}