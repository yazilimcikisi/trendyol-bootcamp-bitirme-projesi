package com.trendyol.customersproduct.repositories;

import com.trendyol.customersproduct.entities.CustomersProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomersProductRepository extends JpaRepository<CustomersProduct,Long> {
    CustomersProduct findByProductId(long productId);
    List<CustomersProduct> findAllByCustomerId(String customerId);
}
