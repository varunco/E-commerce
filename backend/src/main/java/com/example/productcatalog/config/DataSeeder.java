package com.example.productcatalog.config;

import com.example.productcatalog.model.Category;
import com.example.productcatalog.model.Product;
import com.example.productcatalog.repository.CategoryRepository;
import com.example.productcatalog.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedDatabase(
            CategoryRepository categoryRepository,
            ProductRepository productRepository) {

        return args -> {

            // =========================================================
            // CREATE CATEGORIES
            // =========================================================

            String[] categoryNames = {
                    "Electronics",
                    "Clothing",
                    "Footwear",
                    "Books",
                    "Home & Kitchen",
                    "Sports",
                    "Beauty",
                    "Grocery",
                    "Accessories"
            };

            for (String name : categoryNames) {

                if (categoryRepository.findByName(name).isEmpty()) {

                    Category category = new Category();
                    category.setName(name);

                    categoryRepository.save(category);
                }
            }

            // =========================================================
            // DON'T DUPLICATE PRODUCTS
            // =========================================================

            if (productRepository.count() > 0) {

                System.out.println(
                        "Products already exist. Skipping product seeding."
                );

                return;
            }

            System.out.println("Seeding products...");

            // =========================================================
            // ELECTRONICS
            // =========================================================

            Category electronics =
                    categoryRepository.findByName("Electronics")
                            .orElseThrow();

            saveProduct(
                    productRepository,
                    "iPhone 16",
                    "Apple iPhone 16 with A18 chip, Camera Control and advanced camera system.",
                    "https://m.media-amazon.com/images/I/61135j8fPJL._AC_UY218_.jpg",
                    66900,
                    electronics
            );

            saveProduct(
                    productRepository,
                    "Samsung Galaxy S25",
                    "Samsung Galaxy S25 5G with Snapdragon 8 Elite processor and Galaxy AI.",
                    "https://m.media-amazon.com/images/I/61p3FwE31-L._AC_UY218_.jpg",
                    80999,
                    electronics
            );

            saveProduct(
                    productRepository,
                    "MacBook Air M4",
                    "Apple MacBook Air with M4 chip, 13-inch display, 16GB memory and 256GB storage.",
                    "https://m.media-amazon.com/images/I/71CjP9jmqZL._AC_UY218_.jpg",
                    94990,
                    electronics
            );

            saveProduct(
                    productRepository,
                    "Sony WH-1000XM5",
                    "Sony wireless headphones with industry-leading noise cancellation.",
                    "https://m.media-amazon.com/images/I/51KGPDttQhL._AC_UY218_.jpg",
                    24991,
                    electronics
            );

            saveProduct(
                    productRepository,
                    "iPad Air M3",
                    "Apple iPad Air 11-inch with M3 chip, Liquid Retina display and Wi-Fi connectivity.",
                    "https://m.media-amazon.com/images/I/71uC-SeFZkL._AC_UY218_.jpg",
                    68399,
                    electronics
            );

            saveProduct(
                    productRepository,
                    "Logitech MX Master 3S",
                    "Wireless ergonomic mouse with 8K DPI tracking and MagSpeed scrolling.",
                    "https://m.media-amazon.com/images/I/61ni3t1ryQL._AC_UY218_.jpg",
                    8999,
                    electronics
            );

            saveProduct(
                    productRepository,
                    "boAt Airdopes 163",
                    "True wireless earbuds with 40-hour playback, fast charging and IPX5 protection.",
                    "https://m.media-amazon.com/images/I/61QeGcag85L._AC_UY218_.jpg",
                    799,
                    electronics
            );

            saveProduct(
                    productRepository,
                    "JBL Tune 770NC",
                    "Wireless over-ear headphones with active noise cancellation and up to 70-hour battery life.",
                    "https://m.media-amazon.com/images/I/71TvdUf4kyL._AC_UY218_.jpg",
                    5498,
                    electronics
            );

            saveProduct(
                    productRepository,
                    "Kindle Paperwhite 2024",
                    "Amazon Kindle Paperwhite 12th generation with 7-inch display and 16GB storage.",
                    "https://m.media-amazon.com/images/I/516ioi1kzGL._AC_UY218_.jpg",
                    16999,
                    electronics
            );

            saveProduct(
                    productRepository,
                    "Lenovo IdeaPad 1",
                    "Lenovo IdeaPad laptop with AMD Ryzen processor, 16GB RAM and 512GB SSD.",
                    "https://m.media-amazon.com/images/I/61QU5cqY1gL._AC_UY218_.jpg",
                    39990,
                    electronics
            );

            // =========================================================
            // CLOTHING
            // =========================================================

            Category clothing =
                    categoryRepository.findByName("Clothing")
                            .orElseThrow();

            saveProduct(
                    productRepository,
                    "Levi's Graphic Print Crew Neck T-Shirt",
                    "Men's graphic print crew neck cotton T-shirt.",
                    "https://levi.in/cdn/shop/files/A79730124_01_Styleshot.jpg?v=1737030043",
                    999,
                    clothing
            );

            saveProduct(
                    productRepository,
                    "Levi's Logo Printed Slim Fit T-Shirt",
                    "Men's logo printed slim-fit crew neck T-shirt.",
                    "https://levi.in/cdn/shop/files/169601484_01_Styleshot.jpg",
                    999,
                    clothing
            );

            saveProduct(
                    productRepository,
                    "Levi's Brand Logo Crew Neck T-Shirt",
                    "Classic Levi's brand logo crew neck T-shirt.",
                    "https://levi.in/cdn/shop/files/A79730154_01_Styleshot.jpg",
                    999,
                    clothing
            );

            saveProduct(
                    productRepository,
                    "Levi's Brand Logo Loose Fit T-Shirt",
                    "Relaxed loose-fit T-shirt with Levi's branding.",
                    "https://levi.in/cdn/shop/files/004PW0004_01_Styleshot.jpg",
                    1199,
                    clothing
            );

            saveProduct(
                    productRepository,
                    "Levi's Gold Tab Hoodie",
                    "Men's Gold Tab hooded sweatshirt with a casual fit.",
                    "https://levi.in/cdn/shop/files/A37240004_02_Front_afca8846-3644-44a0-a92b-eda957a792c2.jpg?v=1695738126",
                    1999,
                    clothing
            );

            saveProduct(
                    productRepository,
                    "Levi's Solid Black Hooded Sweatshirt",
                    "Solid black cotton hooded sweatshirt with full sleeves.",
                    "https://levi.in/cdn/shop/files/002BO0002_01_Styleshot.jpg?v=1762859237",
                    1811,
                    clothing
            );

            saveProduct(
                    productRepository,
                    "Levi's Solid Light Grey Hooded Sweatshirt",
                    "Light grey solid hooded sweatshirt for casual everyday wear.",
                    "https://levi.in/cdn/shop/files/002BO0001_01_Styleshot.jpg",
                    1811,
                    clothing
            );

            saveProduct(
                    productRepository,
                    "Levi's Graphic Print Hooded Sweatshirt",
                    "Graphic print hooded sweatshirt with a casual fit.",
                    "https://levi.in/cdn/shop/files/A26630001_01_Front_716a5505-cb1d-4dc8-bb11-095a3f2f6db7.jpg",
                    999,
                    clothing
            );

            saveProduct(
                    productRepository,
                    "Levi's Pin Striped Oxford Overshirt",
                    "Light blue relaxed-fit hooded Oxford overshirt with pin stripes.",
                    "https://levi.in/cdn/shop/files/005TX0002_01_Styleshot.jpg?v=1766317163",
                    1975,
                    clothing
            );

            saveProduct(
                    productRepository,
                    "Levi's Checkered Oxford Overshirt",
                    "White relaxed-fit checkered cotton Oxford overshirt.",
                    "https://levi.in/cdn/shop/files/005TX0000_01_Styleshot.jpg?v=1767002673",
                    2089,
                    clothing
            );

            // =========================================================
            // FOOTWEAR
            // =========================================================

            Category footwear =
                    categoryRepository.findByName("Footwear")
                            .orElseThrow();

            saveProduct(
                    productRepository,
                    "Kalenji Jogflow 100",
                    "Men running shoes with cushioning for everyday running up to 10 km per week.",
                    "https://img.tatacliq.com/images/i25/1348Wx2000H/MP000000027150658_1348Wx2000H_202506282240011.jpeg",
                    1599,
                    footwear
            );

            saveProduct(
                    productRepository,
                    "Kalenji Run One",
                    "Lightweight everyday running shoes with comfortable cushioning.",
                    "https://img.tatacliq.com/images/i25/1348Wx2000H/MP000000027150658_1348Wx2000H_202506282239592.jpeg",
                    999,
                    footwear
            );

            saveProduct(
                    productRepository,
                    "Kiprun Jogflow 190.1",
                    "Men running shoes designed for regular running up to 20 km per week.",
                    "https://i.flash.tech/a/rs:fill:300:400:0/g:sm/plain/s3://flash-creatives/images/merchants/decathlon",
                    2499,
                    footwear
            );

            saveProduct(
                    productRepository,
                    "Kiprun Kipcore",
                    "Performance running shoes designed for comfortable longer-distance running.",
                    "https://i.flash.tech/a/rs:fill:300:400:0/g:sm/plain/s3://flash-creatives/images/merchants/decathlon",
                    4999,
                    footwear
            );

            saveProduct(
                    productRepository,
                    "Adidas Samba OG",
                    "Classic Adidas lifestyle shoes with the iconic Samba silhouette.",
                    "https://images.unsplash.com/photo-1542291026-7eec264c27ff",
                    10999,
                    footwear
            );

            saveProduct(
                    productRepository,
                    "Adidas Duramo SL",
                    "Lightweight running shoes designed for everyday training and running.",
                    "https://images.unsplash.com/photo-1552346154-21d32810aba3",
                    5599,
                    footwear
            );

            saveProduct(
                    productRepository,
                    "Adidas Grand Court 3.0",
                    "Classic casual sneakers designed for everyday lifestyle wear.",
                    "https://images.unsplash.com/photo-1549298916-b41d501d3772",
                    7999,
                    footwear
            );

            saveProduct(
                    productRepository,
                    "Adidas Adilette Slides",
                    "Comfortable classic slides suitable for casual everyday use.",
                    "https://images.unsplash.com/photo-1603487742131-4160ec999306",
                    1999,
                    footwear
            );

            saveProduct(
                    productRepository,
                    "Adidas VS Pace 2.0",
                    "Casual lifestyle sneakers with a clean classic design.",
                    "https://images.unsplash.com/photo-1491553895911-0055eca6402d",
                    5999,
                    footwear
            );

            saveProduct(
                    productRepository,
                    "Adidas Terrex Hiking Shoes",
                    "Outdoor shoes designed for hiking and trail activities.",
                    "https://images.unsplash.com/photo-1460353581641-37baddab0fa2",
                    8999,
                    footwear
            );

            // =========================================================
            // BOOKS
            // =========================================================

            Category books =
                    categoryRepository.findByName("Books")
                            .orElseThrow();

            saveProduct(
                    productRepository,
                    "Atomic Habits",
                    "Practical guide to building good habits and breaking bad ones.",
                    "https://covers.openlibrary.org/b/isbn/9780735211292-L.jpg",
                    599,
                    books
            );

            saveProduct(
                    productRepository,
                    "Clean Code",
                    "A practical guide to writing clean, readable and maintainable software.",
                    "https://covers.openlibrary.org/b/isbn/9780132350884-L.jpg",
                    899,
                    books
            );

            saveProduct(
                    productRepository,
                    "Head First Java",
                    "Beginner-friendly introduction to Java programming and object-oriented concepts.",
                    "https://covers.openlibrary.org/b/isbn/9780596009205-L.jpg",
                    799,
                    books
            );

            saveProduct(
                    productRepository,
                    "Introduction to Algorithms",
                    "Comprehensive reference covering algorithms, data structures and algorithmic techniques.",
                    "https://covers.openlibrary.org/b/isbn/9780262033848-L.jpg",
                    1499,
                    books
            );

            saveProduct(
                    productRepository,
                    "Computer Networking",
                    "Introduction to networking concepts, protocols, architectures and applications.",
                    "https://covers.openlibrary.org/b/isbn/9780136681557-L.jpg",
                    1199,
                    books
            );

            saveProduct(
                    productRepository,
                    "Operating System Concepts",
                    "Comprehensive introduction to operating systems, processes, memory and storage.",
                    "https://covers.openlibrary.org/b/isbn/9781119456339-L.jpg",
                    1299,
                    books
            );

            saveProduct(
                    productRepository,
                    "Database System Concepts",
                    "Introduction to database systems, SQL, transactions and database design.",
                    "https://covers.openlibrary.org/b/isbn/9780078022159-L.jpg",
                    1099,
                    books
            );

            saveProduct(
                    productRepository,
                    "Hands-On Machine Learning",
                    "Practical guide to machine learning using modern Python-based techniques.",
                    "https://covers.openlibrary.org/b/isbn/9781098125974-L.jpg",
                    1499,
                    books
            );

            saveProduct(
                    productRepository,
                    "Artificial Intelligence: A Modern Approach",
                    "Comprehensive textbook covering artificial intelligence concepts and techniques.",
                    "https://covers.openlibrary.org/b/isbn/9780134610993-L.jpg",
                    1799,
                    books
            );

            saveProduct(
                    productRepository,
                    "System Design Interview",
                    "Practical preparation guide for designing scalable software systems.",
                    "https://covers.openlibrary.org/b/isbn/9781736049141-L.jpg",
                    999,
                    books
            );

            // =========================================================
            // HOME & KITCHEN
            // =========================================================

            Category homeKitchen =
                    categoryRepository.findByName("Home & Kitchen")
                            .orElseThrow();

            saveProduct(
                    productRepository,
                    "iBELL Multipurpose Electric Kettle",
                    "1.2 litre multipurpose electric kettle with cooking pots and egg boiler.",
                    "https://m.media-amazon.com/images/I/61mKl9VorGL.jpg",
                    1299,
                    homeKitchen
            );

            saveProduct(
                    productRepository,
                    "Rusabl Bamboo Chopping Board",
                    "Bamboo chopping board with metal handle for vegetables, fruits and food preparation.",
                    "https://m.media-amazon.com/images/I/71vVnb0l6LL._SL1500_.jpg",
                    309,
                    homeKitchen
            );

            saveProduct(
                    productRepository,
                    "Amazon Solimo Granite Fry Pan",
                    "22 cm granite-finish non-stick frying pan with glass lid and induction base.",
                    "https://m.media-amazon.com/images/I/810IdpV6BoL.jpg",
                    486,
                    homeKitchen
            );

            saveProduct(
                    productRepository,
                    "Stainless Steel Honeycomb Frying Pan",
                    "30 cm stainless-steel non-stick frying pan with honeycomb coating and lid.",
                    "https://m.media-amazon.com/images/I/51f232TTenL._AC_UF894%2C1000_QL80_.jpg",
                    1699,
                    homeKitchen
            );

            saveProduct(
                    productRepository,
                    "Amazon Solimo Stainless Steel Dinner Set",
                    "36-piece stainless-steel dinner set with mirror finish.",
                    "https://m.media-amazon.com/images/I/81mWn27Q5XL.jpg",
                    1629,
                    homeKitchen
            );

            // =========================================================
            // SPORTS
            // =========================================================

            Category sports =
                    categoryRepository.findByName("Sports")
                            .orElseThrow();

            saveProduct(
                    productRepository,
                    "Mars Cricket Bat",
                    "Full-size cricket bat suitable for tennis-ball and recreational cricket.",
                    "https://m.media-amazon.com/images/I/61-KGcy0jpL.jpg",
                    899,
                    sports
            );

            saveProduct(
                    productRepository,
                    "Hill C Popular Willow Cricket Bat",
                    "Full-size popular willow cricket bat with rubber grip.",
                    "https://m.media-amazon.com/images/I/41nz%2BjDWWeL.jpg",
                    489,
                    sports
            );

            saveProduct(
                    productRepository,
                    "Vector X Attacker Football",
                    "Size 5 synthetic moulded football for training and recreational play.",
                    "https://m.media-amazon.com/images/I/8140K8P-8pL._AC_UL320_.jpg",
                    445,
                    sports
            );

            saveProduct(
                    productRepository,
                    "Li-Ning Windstorm 72 Badminton Racket",
                    "Lightweight badminton racket designed for recreational and intermediate players.",
                    "https://m.media-amazon.com/images/I/51fl+zlNzuL._AC_UL320_.jpg",
                    2499,
                    sports
            );

            saveProduct(
                    productRepository,
                    "SLOVIC Resistance Bands",
                    "Resistance band set for strength training, stretching and home workouts.",
                    "https://m.media-amazon.com/images/I/715jCK-tdJL.jpg",
                    949,
                    sports
            );

            saveProduct(
                    productRepository,
                    "Pull Up Resistance Bands Set",
                    "Multi-level resistance bands for assisted pull-ups, stretching and strength training.",
                    "https://m.media-amazon.com/images/I/7119ZcztNmL.jpg",
                    999,
                    sports
            );

            saveProduct(
                    productRepository,
                    "Boldfit Resistance Band",
                    "Light resistance fitness band for Pilates, stretching and home workouts.",
                    "https://m.media-amazon.com/images/I/61CRk5jWXDL.jpg",
                    271,
                    sports
            );

            saveProduct(
                    productRepository,
                    "Lifelong Yoga Mat",
                    "4mm EVA anti-slip yoga mat for gym, yoga and home workouts.",
                    "https://m.media-amazon.com/images/I/51nRJsOppZL.jpg",
                    299,
                    sports
            );

            saveProduct(
                    productRepository,
                    "Weighted Skipping Rope",
                    "Adjustable weighted skipping rope designed for cardio and fitness training.",
                    "https://m.media-amazon.com/images/I/51d3TrsSlCL.jpg",
                    949,
                    sports
            );

            saveProduct(
                    productRepository,
                    "Home Gym Resistance Band Kit",
                    "Multi-piece resistance training kit with bands, handles, ankle straps and door anchor.",
                    "https://m.media-amazon.com/images/I/51ySTOZfy3L._AC_UF894,1000_QL80_.jpg",
                    209,
                    sports
            );

            // =========================================================
            // BEAUTY
            // =========================================================

            Category beauty =
                    categoryRepository.findByName("Beauty")
                            .orElseThrow();

            saveProduct(
                    productRepository,
                    "Simple Kind To Skin Refreshing Facial Wash",
                    "Gentle soap-free facial cleanser with Pro-Vitamin B5 and Vitamin E.",
                    "https://m.media-amazon.com/images/I/51FQerCM8yL.jpg",
                    249,
                    beauty
            );

            saveProduct(
                    productRepository,
                    "Lakme Absolute Matte Revolution Lip Color",
                    "Matte lipstick with rich colour and a smooth finish.",
                    "https://www.bbassets.com/media/uploads/p/l/40183232-2_7-lakme-absolute-matte-revolution-lip-color-101-bombshell-red.jpg",
                    330,
                    beauty
            );

            saveProduct(
                    productRepository,
                    "Nykaa Naturals Fermented Rice Water Shampoo",
                    "Fermented rice water and bamboo shampoo for dry and damaged hair.",
                    "https://images-static.nykaa.com/media/catalog/product/8/2/82f0558NYKAB00000551_MP.jpg",
                    499,
                    beauty
            );

            saveProduct(
                    productRepository,
                    "Cosmoist Moisturizer Lotion",
                    "24-hour moisturizing lotion with squalene, shea butter and Omega 3.",
                    "https://images.apollo247.in/pub/media/catalog/product/c/o/cos0269_1.jpg",
                    399,
                    beauty
            );

            saveProduct(
                    productRepository,
                    "Origine Naturespired Shampoo",
                    "Shampoo for normal hair with Bhringraj and Amla.",
                    "https://meds.myupchar.com/84305/Shampoo_FOR_NORMAL_HAIR_With_Box.jpg",
                    349,
                    beauty
            );

            saveProduct(
                    productRepository,
                    "Mamaearth Nourishing Natural Lip Balm",
                    "Natural lip balm with Vitamin E and Shea Butter.",
                    "https://assets.myntassets.com/assets/images/20510832/2024/3/12/fd23ca7a-1751-462c-9f64-636522c65f3c1710221056737MamaearthNourishing100NaturalLipBalm-4g9.jpg",
                    199,
                    beauty
            );

            saveProduct(
                    productRepository,
                    "Mamaearth HydraGel Indian Sunscreen SPF 50",
                    "Lightweight gel sunscreen with SPF 50 for everyday sun protection.",
                    "https://www.pakcosmetics.com/images/content/productimgLarge/MamaEarthHydraGelIndianSunscreenSPF50.jpg",
                    499,
                    beauty
            );

            saveProduct(
                    productRepository,
                    "Simple Hydrating Light Moisturiser",
                    "Lightweight facial moisturiser designed for daily hydration.",
                    "https://cdn.tirabeauty.com/v2/billowing-snowflake-434234/original/Tira_Combos/Lakme/TIRA009145/1013410_combo_37/1013410_combo_37_1.jpg",
                    399,
                    beauty
            );

            saveProduct(
                    productRepository,
                    "Plum Alpha Arbutin Face Serum",
                    "Brightening facial serum with Alpha Arbutin and Hyaluronic Acid.",
                    "https://smytten-image.gumlet.io/discover_product/1715330569_SKin%20%26%20Hair%20essentials%20Kit_D1.jpg",
                    599,
                    beauty
            );

            saveProduct(
                    productRepository,
                    "Lakme Sun Expert SPF 50 Sunscreen",
                    "Tinted matte sunscreen offering broad-spectrum SPF 50 protection.",
                    "https://cdn.tirabeauty.com/v2/billowing-snowflake-434234/original/Tira_Combos/Lakme/TIRA009145/1013410_combo_43/1013410_combo_43_1.jpg",
                    499,
                    beauty
            );

            // =========================================================
            // GROCERY
            // =========================================================

            Category grocery =
                    categoryRepository.findByName("Grocery")
                            .orElseThrow();

            saveProduct(
                    productRepository,
                    "Daawat Rozana Basmati Rice Niki 10kg",
                    "Long-grain basmati rice suitable for everyday meals.",
                    "https://m.media-amazon.com/images/I/91edmG+n15L.jpg",
                    1299,
                    grocery
            );

            saveProduct(
                    productRepository,
                    "Daawat Rozana Basmati Rice Super 5kg",
                    "Everyday basmati rice with long, fluffy grains.",
                    "https://m.media-amazon.com/images/I/711e9Lalv7L.jpg",
                    493,
                    grocery
            );

            saveProduct(
                    productRepository,
                    "India Gate Everyday Basmati Rice 5kg",
                    "Aged basmati rice with fluffy, non-sticky grains.",
                    "https://m.media-amazon.com/images/I/71v0fCOgbjL.jpg",
                    326,
                    grocery
            );

            saveProduct(
                    productRepository,
                    "Quaker Rolled Oats 2kg",
                    "100% natural wholegrain rolled oats for breakfast and healthy recipes.",
                    "https://m.media-amazon.com/images/I/51qeOJlj8yL.jpg",
                    322,
                    grocery
            );

            saveProduct(
                    productRepository,
                    "Pintola Crunchy Peanut Butter 1kg",
                    "Natural crunchy peanut butter made from roasted peanuts.",
                    "https://m.media-amazon.com/images/I/61thAKEf8vL._AC_UL320_.jpg",
                    379,
                    grocery
            );

            saveProduct(
                    productRepository,
                    "Pintola Creamy Peanut Butter 1kg",
                    "Smooth natural peanut butter with no added sugar.",
                    "https://m.media-amazon.com/images/I/91mhIMRd7FL._AC_UL320_.jpg",
                    376,
                    grocery
            );

            saveProduct(
                    productRepository,
                    "Fortune Chakki Fresh Atta 10kg",
                    "Whole wheat flour made using the traditional chakki process.",
                    "https://m.media-amazon.com/images/I/81a1cMJf1tL.jpg",
                    411,
                    grocery
            );

            saveProduct(
                    productRepository,
                    "Wonderland Mixed Dry Fruits",
                    "Premium mixture of almonds, cashews, raisins and other dry fruits.",
                    "https://m.media-amazon.com/images/I/61MpsGmHPgL.jpg",
                    135,
                    grocery
            );

            saveProduct(
                    productRepository,
                    "Nature Purify Mixed Nuts 1kg",
                    "Premium mixed nuts including almonds, cashews, pistachios and walnuts.",
                    "https://m.media-amazon.com/images/I/61aDUQR4R2L.jpg",
                    415,
                    grocery
            );

            saveProduct(
                    productRepository,
                    "Amazon Signature Coffee 250g",
                    "100% Arabica freeze-dried coffee powder.",
                    "https://www.meshopindia.com/product-images/2.jpg/1266016000023267025/1100x1100",
                    299,
                    grocery
            );

            // =========================================================
            // ACCESSORIES
            // =========================================================

            Category accessories =
                    categoryRepository.findByName("Accessories")
                            .orElseThrow();

            saveProduct(
                    productRepository,
                    "Zaisefast 55L Trekking Backpack Set",
                    "55L trekking backpack suitable for travel, hiking and outdoor activities.",
                    "https://images.meesho.com/images/products/274291679/8or5g_512.webp?width=512",
                    999,
                    accessories
            );

            saveProduct(
                    productRepository,
                    "Blubags Backpack with Cap and Sunglasses",
                    "Everyday backpack set with cap and sunglasses.",
                    "https://assets.myntassets.com/assets/images/2025/JUNE/25/RTY5pLnN_ae573ee1c94f4243be829b6ff2afdd94.jpg",
                    899,
                    accessories
            );

            saveProduct(
                    productRepository,
                    "30L Laptop Backpack Combo",
                    "30L backpack with multiple compartments suitable for college, office and travel.",
                    "https://images.meesho.com/images/products/561150487/npxzl_512.webp?width=512",
                    799,
                    accessories
            );

            saveProduct(
                    productRepository,
                    "American Tourister Accessories Gift Set",
                    "Backpack with wallet, belt and other everyday accessories.",
                    "https://www.oyegifts.com/cdn/shop/files/gifts-54.jpg?v=1773747893",
                    1499,
                    accessories
            );

            saveProduct(
                    productRepository,
                    "Mens Five Piece Accessories Combo",
                    "Accessory set containing cap, sunglasses, belt, wallet and watch.",
                    "https://images.meesho.com/images/products/297433774/d56hs_512.jpg",
                    699,
                    accessories
            );

            saveProduct(
                    productRepository,
                    "Lee Black Genuine Leather Wallet",
                    "Black genuine leather bi-fold wallet with multiple card compartments.",
                    "https://static.aceomni.cmsaceturtle.com/prod/product-image/aceomni/Lee/Monobrand/LMAWT00003/LMAWT00003_1.jpg",
                    1699,
                    accessories
            );

            saveProduct(
                    productRepository,
                    "Lee Black Leather Wallet Classic",
                    "Classic black leather wallet designed for everyday use.",
                    "https://static.aceomni.cmsaceturtle.com/prod/product-image/aceomni/Lee/Monobrand/LMAWT00023/LMAWT00023_1.jpg",
                    1699,
                    accessories
            );

            saveProduct(
                    productRepository,
                    "Lee Black Leather Wallet Premium",
                    "Premium genuine leather wallet with multiple card slots.",
                    "https://static.aceomni.cmsaceturtle.com/prod/product-image/aceomni/Lee/Monobrand/LMAWT00014/LMAWT00014_1.jpg",
                    1699,
                    accessories
            );

            saveProduct(
                    productRepository,
                    "S.T. Dupont Line D Wallet",
                    "Black smooth leather wallet with six card slots and money clip.",
                    "https://st-dupont.co.in/cdn/shop/files/180001_1_09af97ec-9710-4aca-8479-ea3df88a8412.png?v=1773725717",
                    46868,
                    accessories
            );

            saveProduct(
                    productRepository,
                    "Genuine Leather Mens Belt",
                    "Brown genuine leather belt with durable metal buckle.",
                    "https://i5.walmartimages.com/asr/699f3576-3bd0-4f9e-b520-06b6f9e4693a.671212d2c328e115d9c429e499dc290f.jpeg?odnBg=FFFFFF&odnHeight=768&odnWidth=768",
                    899,
                    accessories
            );

            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "PRODUCT SEEDING COMPLETED"
            );

            System.out.println(
                    "Total products: " +
                            productRepository.count()
            );

            System.out.println(
                    "=========================================="
            );
        };
    }

    // =========================================================
    // HELPER METHOD
    // =========================================================

    private void saveProduct(
            ProductRepository productRepository,
            String name,
            String description,
            String imageUrl,
            double price,
            Category category) {

        Product product = new Product();

        product.setName(name);
        product.setDescription(description);
        product.setImageURL(imageUrl);
        product.setPrice(price);
        product.setCategory(category);

        productRepository.save(product);
    }
}