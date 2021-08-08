package com.trendyol.basket.model;

import org.springframework.data.couchbase.core.mapping.Field;

public class ProductModel {
    private long id;
    private String title;
    private String image;
    private int quantity;
    private float price;

    public ProductModel(long id, String title, String image, int quantity, float price) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
