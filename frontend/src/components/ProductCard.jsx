import { Card, Badge } from "react-bootstrap";

export default function ProductCard({ product }) {

    return (
        <Card className="product-card h-100">

            <div className="product-image-wrapper">

                <img
                    src={product.imageURL}
                    alt={product.name}
                    className="product-image"
                />

                <Badge className="category-badge">
                    {product.category?.name}
                </Badge>

            </div>

            <Card.Body>

                <Card.Title>
                    {product.name}
                </Card.Title>

                <Card.Text className="product-description">
                    {product.description}
                </Card.Text>

                <div className="product-bottom">

                    <span className="product-price">
                        ₹{product.price?.toLocaleString("en-IN")}
                    </span>

                    <button className="view-button">
                        View
                    </button>

                </div>

            </Card.Body>

        </Card>
    );
}