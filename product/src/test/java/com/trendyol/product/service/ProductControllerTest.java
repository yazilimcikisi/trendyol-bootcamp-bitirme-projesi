package com.trendyol.product.service;

import com.trendyol.product.controllers.ProductController;
import com.trendyol.product.entites.Product;
import com.trendyol.product.repositories.ProductRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Fetches a document by it's id. Therefore it does not need any additional secondary index.
     */
    @Test
    @Order(1)
    public void findById() {
        Product product = new Product("ProductTitle2", "imageURL", 5, 10);
        //Given
        Object createdSeller = productController.addNewProduct(product);
        //When

        //Then
        //assertEquals("myShop", foundSeller.getName());
    }
}
