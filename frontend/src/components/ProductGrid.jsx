import { Row, Col } from "react-bootstrap";
import ProductCard from "./ProductCard";

export default function ProductGrid({ products, loading }) {

    if (loading) {
        return (
            <div className="loading-state">
                Loading products...
            </div>
        );
    }

    if (!products.length) {
        return (
            <div className="empty-state">
                <h4>No products found</h4>
                <p>Try another search or category.</p>
            </div>
        );
    }

    return (
        <Row className="g-4">

            {products.map(product => (

                <Col
                    key={product.id}
                    xs={12}
                    sm={6}
                    lg={4}
                    xl={3}
                >
                    <ProductCard product={product} />
                </Col>

            ))}

        </Row>
    );
}