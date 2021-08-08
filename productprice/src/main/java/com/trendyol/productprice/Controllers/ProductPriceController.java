package com.trendyol.productprice.Controllers;

import com.trendyol.productprice.Entities.ProductPrice;
import com.trendyol.productprice.Repositories.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/productPrice")
public class ProductPriceController {
    private final ProductPriceRepository _productPriceRepository;

    @Autowired
    public ProductPriceController(ProductPriceRepository productPriceRepository)
    {
        _productPriceRepository = productPriceRepository;
    }

    @PostMapping(path = "/addNewProductStock/{productId}/{price}")
    public ProductPrice addNewCustomersProduct(@PathVariable("productId") long productId, @PathVariable("price") float price)
    {
        ProductPrice productPrice = new ProductPrice(productId, price);
         return _productPriceRepository.save(productPrice);
    }

    @GetMapping(path = "/getProductStockByProductId")
    public ProductPrice getProductPriceByProductId(@RequestParam(value = "productId") long productId) {
        var productPrice =  _productPriceRepository.findByProductId(productId);
        return productPrice;
    }

}
