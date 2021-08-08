package com.trendyol.basket.model;

import java.util.List;

public class BasketResultModel {
    private List<ProductModel> products;
    private BasketInfoModel basketInfo;

    public BasketResultModel(List<ProductModel> products) {
        this.products = products;

        float subtotal = 0f;
        for (ProductModel product: products) {
            subtotal += (product.getPrice()* product.getQuantity());
        }
        basketInfo = new BasketInfoModel(subtotal);
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }

    public BasketInfoModel getBasketInfo() {
        return basketInfo;
    }

    public void setBasketInfo(BasketInfoModel basketInfo) {
        this.basketInfo = basketInfo;
    }
}
