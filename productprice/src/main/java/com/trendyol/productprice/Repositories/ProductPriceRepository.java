package com.trendyol.productprice.Repositories;

import com.trendyol.productprice.Entities.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice,Long> {
    ProductPrice findByProductId(long productId);
}
