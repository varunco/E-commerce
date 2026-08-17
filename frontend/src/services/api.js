const API_URL = import.meta.env.VITE_API_URL || "http://localhost:8080";

const getToken = () => {
    return localStorage.getItem("token");
};

const request = async (url, options = {}) => {

    const token = getToken();

    const headers = {
        "Content-Type": "application/json",

        ...(token && {
            Authorization: `Bearer ${token}`
        }),

        ...options.headers
    };

    const response = await fetch(
        `${API_URL}${url}`,
        {
            ...options,
            headers
        }
    );

    // Get response text first
    const text = await response.text();

    if (!response.ok) {

        console.error(
            `API Error ${response.status}:`,
            text
        );

        throw new Error(
            `Request failed: ${response.status}`
        );
    }

    // DELETE 204 responses have no body
    if (!text) {
        return null;
    }

    // Parse JSON only when there is content
    try {
        return JSON.parse(text);
    } catch {
        return text;
    }
};


/* =========================
   AUTH
========================= */

export const login = (data) =>
    request("/api/auth/login", {
        method: "POST",
        body: JSON.stringify(data)
    });


export const signup = (data) =>
    request("/api/auth/register", {
        method: "POST",
        body: JSON.stringify(data)
    });


/* =========================
   PRODUCTS
========================= */

export const getProducts = () =>
    request("/api/products");


export const getProduct = (id) =>
    request(`/api/products/${id}`);


export const searchProducts = (name) =>
    request(
        `/api/products/search?name=${encodeURIComponent(name)}`
    );


export const getCategories = () =>
    request("/api/categories");


export const getProductsByCategory = (id) =>
    request(`/api/products/category/${id}`);


/* =========================
   AI
========================= */

export const createAISession = () =>
    request("/api/ai/session", {
        method: "POST"
    });


export const sendAIMessage = (sessionId, message) =>
    request(`/api/ai/session/${sessionId}`, {
        method: "POST",
        body: JSON.stringify({
            message
        })
    });


export const getAIMessages = (sessionId) =>
    request(`/api/ai/session/${sessionId}`);


export const deleteAISession = (sessionId) =>
    request(`/api/ai/session/${sessionId}`, {
        method: "DELETE"
    });