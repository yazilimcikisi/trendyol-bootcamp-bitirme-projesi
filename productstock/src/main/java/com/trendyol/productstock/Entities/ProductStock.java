package com.trendyol.productstock.Entities;

import javax.persistence.*;
import java.security.PublicKey;

@Entity
@Table(name = "productStocks")
public class ProductStock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long productId;
    private int stock;

    public ProductStock(){}

    public ProductStock(long productId, int stock) {
        this.productId = productId;
        this.stock = stock;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
