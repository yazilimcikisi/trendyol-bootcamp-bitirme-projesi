package com.trendyol.productstock.Repositories;

import com.trendyol.productstock.Entities.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock,Long> {
    ProductStock findByProductId(long productId);
}
