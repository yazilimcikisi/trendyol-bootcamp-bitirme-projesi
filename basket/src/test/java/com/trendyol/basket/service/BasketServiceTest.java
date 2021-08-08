package com.trendyol.basket.service;

import com.google.gson.Gson;
import com.trendyol.basket.domain.Basket;
import com.trendyol.basket.domain.Product;
import com.trendyol.basket.repository.BasketRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BasketServiceTest {
    @Autowired
    private BasketService basketService;
    @Autowired
    private BasketRepository basketRepository;

    /**
     * Add a product to customers basket
     */
    @Test
    @Order(1)
    public void addProductToCustomersBasket() {
        //Given
        String customerId = "d9132c34-7c7f-4863-892b-f81c458a88a4";
        long productId = Long.valueOf(1);

        //When
        basketService.addProductToCustomerBasket(customerId, productId);

        //Then
        List<Basket> basketList = basketRepository.findAllByCustomerId(customerId);
        assertEquals(1,basketList.size());

        Basket basket = basketRepository.findByCustomerId(customerId);
        assertEquals(1,basket.getProductList().stream().filter(product -> product.getId() == productId).count());
    }


    /**
     * Add a product to customers basket
     */
    @Test
    @Order(2)
    public void changeProductQuantityFromBasket() {
        //Given
        String customerId = "d9132c34-7c7f-4863-892b-f81c458a88a4";
        long productId = Long.valueOf(1);
        int quantity = 4;
        Basket currentBasket = basketRepository.findByCustomerId(customerId);
        basketRepository.delete(currentBasket);
        basketService.addProductToCustomerBasket(customerId,productId);

        //When
        basketService.changeProductQuantityFromBasket(customerId, productId, quantity);

        //Then
        List<Basket> basketList = basketRepository.findAllByCustomerId(customerId);
        assertEquals(1,basketList.size());

        Basket basket = basketRepository.findByCustomerId(customerId);
        assertEquals(1,basket.getProductList().stream().filter(product -> product.getId() == productId).count());

        for (Product product: basket.getProductList()) {
            if (product.getId() == productId)
            {
                assertEquals(quantity, product.getQuantity());
            }
        }
    }

    /**
     * Delete a product from customers basket
     */
    @Test
    @Order(3)
    public void deleteProductFromCustomersBasketById() {
        //Given
        String customerId = "d9132c34-7c7f-4863-892b-f81c458a88a4";
        long productId = Long.valueOf(3);

        //When
        basketService.deleteProductFromCustomersBasketById(customerId,productId);

        //Then
        List<Basket> basketList = basketRepository.findAllByCustomerId(customerId);
        assertEquals(1,basketList.size());

        Basket basket = basketRepository.findByCustomerId(customerId);
        assertEquals(0,basket.getProductList().stream().filter(product -> product.getId() == productId).count());
    }

    /**
     * Get customers basket info
     */
    @Test
    @Order(4)
    public void getCustomersBasketInfo() {
        //Given
        String customerId = "d9132c34-7c7f-4863-892b-f81c458a88a4";
        long productId = Long.valueOf(1);
        basketService.addProductToCustomerBasket(customerId,productId);
        basketService.addProductToCustomerBasket(customerId,productId);
        basketService.addProductToCustomerBasket(customerId,productId);
        basketService.addProductToCustomerBasket(customerId,productId);
        productId = Long.valueOf(2);
        basketService.addProductToCustomerBasket(customerId,productId);
        basketService.addProductToCustomerBasket(customerId,productId);

        //When
        var basketInfo = basketService.getCustomersBasketInfo(customerId);
        Gson json = new Gson();
        System.out.println(json.toJson(basketInfo));

        //Then
        //burada nasıl bir kontrol yapmam gerektiğini bilemedim.
    }

}
