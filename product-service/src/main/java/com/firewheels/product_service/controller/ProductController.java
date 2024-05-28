package com.firewheels.product_service.controller;


import com.firewheels.product_service.model.Product;
import com.firewheels.product_service.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    private static final Logger LOGGER = LogManager.getLogger(ProductController.class);
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     *
     * @param product
     * @return
     */
    @PostMapping("/saveproduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        LOGGER.info("Creating product {}", product);
        Product savedProduct = productService.saveProduct(product);
        LOGGER.info("Saved product {}", savedProduct);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    /**
     *
     * @return all products from db
     */
    @GetMapping("/showall")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            LOGGER.info("Fetching all products - entry");
            List<Product> products = productService.getAllProducts();
            LOGGER.info("Fetched all products - exit");
            return ResponseEntity.ok(products);
        }
        catch (Exception e){
            LOGGER.error("{} - Error occurred while fetching all products", e.getMessage());
            throw e;
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
//        Optional<Product> product = productService.getProductById(id);
//        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }

//    @GetMapping("/descriptions")
//    public ResponseEntity<List<String>> getAllProductDescriptions() {
//        List<String> descriptions = productService.getAllProductDescriptions();
//        return ResponseEntity.ok(descriptions);
//    }
//
//    @PostMapping("/addtocart")
//    public ResponseEntity<String> addToCart(@RequestBody CartItemRequest cartItemRequest) {
//        try {
//            productService.addToCart(cartItemRequest);
//            return ResponseEntity.ok("Product added to cart successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding product to cart: " + e.getMessage());
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
//        if (!productService.getProductById(id).isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        product.setId(id);
//        Product updatedProduct = productService.saveProduct(product);
//        return ResponseEntity.ok(updatedProduct);
//    }
//
//    @PutMapping("/{id}/updaterating")
//    public ResponseEntity<Product> updateProductRating(@PathVariable Long id, @RequestParam double rating, @RequestParam String ratingImage) {
//        try {
//            productService.updateProductRating(id, rating, ratingImage);
//            return ResponseEntity.ok().build();
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping("/random")
//    public ResponseEntity<List<Product>> getRandomProducts() {
//        List<Product> randomProducts = productService.getRandomProducts();
//        return ResponseEntity.ok(randomProducts);
//    }
//    @GetMapping("/random2")
//    public ResponseEntity<List<Product>> getRandomProducts2() {
//        List<Product> randomProducts = productService.getRandomProducts();
//        return ResponseEntity.ok(randomProducts);
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        productService.deleteProduct(id);
//        return ResponseEntity.noContent().build();
//    }
}