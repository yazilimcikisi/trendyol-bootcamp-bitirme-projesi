package com.trendyol.customersproduct.entities;

import javax.persistence.*;

@Entity
@Table(name = "customersProducts")
public class CustomersProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long productId;
    private String customerId;

    public CustomersProduct()
    {

    }
    public CustomersProduct(String customerId, long productId) {
        this.customerId = customerId;
        this.productId = productId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
