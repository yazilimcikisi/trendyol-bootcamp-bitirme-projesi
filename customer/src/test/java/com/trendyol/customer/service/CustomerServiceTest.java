package com.trendyol.customer.service;

import com.trendyol.customer.domain.Customer;
import com.trendyol.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Fetches a document by it's id. Therefore it does not need any additional secondary index.
     */
    @Test
    @Order(1)
    public void findById() {
        //Given
        Customer createdCustomer = customerService.create("123456789", "Aybuke", "Arpacı", "test@email.com", "555 55 555", "aybukearpaci");

        //When

        //Then
        //assertEquals("myShop", foundSeller.getName());
    }

}
