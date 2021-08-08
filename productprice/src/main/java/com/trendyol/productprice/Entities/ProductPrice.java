package com.trendyol.productprice.Entities;

import javax.persistence.*;

@Entity
@Table(name = "productPrices")
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long productId;
    private float price;

    public ProductPrice(){}

    public ProductPrice(long productId, float price) {
        this.productId = productId;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
