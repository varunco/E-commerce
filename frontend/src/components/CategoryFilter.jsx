import { Button } from "react-bootstrap";

export default function CategoryFilter({
    categories,
    selected,
    onSelect
}) {

    return (
        <div className="category-scroll">

            <Button
                className={
                    selected === null
                        ? "category-btn active"
                        : "category-btn"
                }
                onClick={() => onSelect(null)}
            >
                All
            </Button>

            {categories.map(category => (

                <Button
                    key={category.id}
                    className={
                        selected === category.id
                            ? "category-btn active"
                            : "category-btn"
                    }
                    onClick={() =>
                        onSelect(category.id)
                    }
                >
                    {category.name}
                </Button>

            ))}

        </div>
    );
}