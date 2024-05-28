package com.firewheels.product_service.service;

import com.firewheels.product_service.model.Product;
import com.firewheels.product_service.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    private static final Logger LOGGER = LogManager.getLogger(ProductService.class);

    public List<Product> getAllProducts() {
        LOGGER.info("Entry: Getting all products");
        List<Product> products = productRepository.findAll();
        LOGGER.info("Exit: Getting all products");
        return products;
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        LOGGER.info("Entry: Saving product");
        Product products = productRepository.save(product);
        LOGGER.info("Exit: Saving product");
        return products;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
