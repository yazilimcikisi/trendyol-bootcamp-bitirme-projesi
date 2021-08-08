package com.trendyol.product.controllers;


import com.trendyol.product.entites.Product;
import com.trendyol.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
    private final ProductRepository _productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        _productRepository = productRepository;
    }

    @RequestMapping(value = "/getProductDetailListByProductIdList", params = "productIdList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getProductDetailListByProductIdList(@RequestParam List<Long> productIdList) {
        if(productIdList.isEmpty()){
            return new ResponseEntity<>("\" Id list is empty \" ", HttpStatus.NOT_FOUND);
        }
        ArrayList<Product> productDetailList = new ArrayList<Product>();
        for (long productId: productIdList) {
            Product product = _productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
            productDetailList.add(product);
        }
        if (!productDetailList.isEmpty()) {
            return new ResponseEntity<>(productDetailList, HttpStatus.OK);
        }
        return new ResponseEntity<>(
                "There isn't any Product" +
                        null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getProductDetailByProductId")
    public Product getProductDetailByProductId(@RequestParam(value = "productId") long productId) {
        var product =  _productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
        return product;
    }

    @PostMapping(path = "/addNewProduct")
    public Object addNewProduct(@RequestBody Product product)
    {
            Product createdProduct = new Product(product.getTitle(), product.getImage(), product.getPrice(), product.getStock());
            createdProduct = _productRepository.save(createdProduct);
            System.out.println("A new Product created with id: " + createdProduct.getId() + "  and name: " + createdProduct.getTitle());
            return createdProduct;
    }

}
