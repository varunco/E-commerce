import { useEffect, useState } from "react";
import {
    Container,
    Form,
    Button
} from "react-bootstrap";

import {
    getProducts,
    getCategories,
    getProductsByCategory,
    searchProducts
} from "../services/api";

import ProductGrid from "../components/ProductGrid";
import CategoryFilter from "../components/CategoryFilter";

export default function Products() {

    const [products, setProducts] = useState([]);
    const [categories, setCategories] = useState([]);

    const [selectedCategory, setSelectedCategory] =
        useState(null);

    const [search, setSearch] = useState("");
    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadInitialData();

    }, []);

    const loadInitialData = async () => {

        try {

            const [productsData, categoriesData] =
                await Promise.all([
                    getProducts(),
                    getCategories()
                ]);

            setProducts(productsData);
            setCategories(categoriesData);

        } catch (error) {

            console.error(error);

        } finally {

            setLoading(false);

        }
    };

    const handleCategory = async id => {

        setSelectedCategory(id);
        setSearch("");

        setLoading(true);

        try {

            const data = id === null
                ? await getProducts()
                : await getProductsByCategory(id);

            setProducts(data);

        } finally {

            setLoading(false);
        }
    };

    const handleSearch = async e => {

        e.preventDefault();

        if (!search.trim()) {
            loadInitialData();
            return;
        }

        setLoading(true);

        try {

            const data =
                await searchProducts(search);

            setProducts(data);

        } finally {

            setLoading(false);
        }
    };

    return (
        <main>

            <section className="hero-section">

                <Container>

                    <div className="hero-content">

                        <span className="hero-label">
                            SMART SHOPPING
                        </span>

                        <h1>
                            Find what you're
                            <span> looking for.</span>
                        </h1>

                        <p>
                            Explore our catalog or ask our AI
                            assistant for personalized recommendations.
                        </p>

                        <Form
                            className="search-form"
                            onSubmit={handleSearch}
                        >

                            <Form.Control
                                placeholder="Search products..."
                                value={search}
                                onChange={e =>
                                    setSearch(e.target.value)
                                }
                            />

                            <Button
                                type="submit"
                                className="primary-button"
                            >
                                Search
                            </Button>

                        </Form>

                    </div>

                </Container>

            </section>

            <Container className="catalog-container">

                <div className="section-heading">

                    <div>
                        <span className="section-label">
                            CATALOG
                        </span>

                        <h2>
                            Explore products
                        </h2>
                    </div>

                    <span className="product-count">
                        {products.length} products
                    </span>

                </div>

                <CategoryFilter
                    categories={categories}
                    selected={selectedCategory}
                    onSelect={handleCategory}
                />

                <ProductGrid
                    products={products}
                    loading={loading}
                />

            </Container>

        </main>
    );
}