package com.trendyol.productstock.Controllers;

import com.trendyol.productstock.Entities.ProductStock;
import com.trendyol.productstock.Repositories.ProductStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path = "/productStock")
public class ProductStockController {
    private final ProductStockRepository _productStockRepository;

    @Autowired
    public ProductStockController(ProductStockRepository productStockRepository)
    {
        _productStockRepository = productStockRepository;
    }

    @PostMapping(path = "/addNewProductStock/{productId}/{stock}")
    public ProductStock addNewCustomersProduct(@PathVariable("productId") long productId, @PathVariable("stock") int stock)
    {
        ProductStock productStock = new ProductStock(productId, stock);
         return _productStockRepository.save(productStock);
    }

    @GetMapping(path = "/getProductStockByProductId")
    public ProductStock getProductStockByProductId(@RequestParam(value = "productId") long productId) {
        var productStock =  _productStockRepository.findByProductId(productId);
        return productStock;
    }

}
