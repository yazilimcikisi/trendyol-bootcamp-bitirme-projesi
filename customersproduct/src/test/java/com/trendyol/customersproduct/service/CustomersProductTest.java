package com.trendyol.customersproduct.service;

import com.trendyol.customersproduct.controllers.CustomersProductController;
import com.trendyol.customersproduct.repositories.CustomersProductRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CustomersProductTest {
    @Autowired
    private CustomersProductRepository customersProductRepository;
    @Autowired
    private CustomersProductController customersProductController;

    /**
     * Fetches a document by it's id. Therefore it does not need any additional secondary index.
     */
    @Test
    @Order(1)
    public void findById() {
        //Given
        //Basket createdBasket = basketService.create("d9132c34-7c7f-4863-892b-f81c458a88a4");

        //When

        //Then
        //assertEquals("myShop", foundSeller.getName());
    }

}

