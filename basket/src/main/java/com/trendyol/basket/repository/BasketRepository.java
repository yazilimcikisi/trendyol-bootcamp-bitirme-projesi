package com.trendyol.basket.repository;

import com.trendyol.basket.domain.Basket;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends CouchbaseRepository<Basket, String> {
    Basket findByCustomerId(String customerId);
    List<Basket> findAllByCustomerId(String  customerId);
}
