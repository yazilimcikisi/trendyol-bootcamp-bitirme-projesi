package com.trendyol.customersproduct.controllers;

import com.trendyol.customersproduct.entities.CustomersProduct;
import com.trendyol.customersproduct.repositories.CustomersProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.xml.transform.sax.SAXResult;
import java.util.List;

@RestController
@RequestMapping(path = "/customersProduct")
public class CustomersProductController {
    private final CustomersProductRepository _customersProductRepository;

    @Autowired
    public  CustomersProductController(CustomersProductRepository customersProductRepository)
    {
        _customersProductRepository = customersProductRepository;
    }

    @PostMapping(path = "/addNewCustomersProduct/{customerId}/{productId}")
    public void addNewCustomersProduct(@PathVariable("customerId") String customerId, @PathVariable("productId") long productId)
    {
        CustomersProduct customersProduct = new CustomersProduct(customerId, productId);
        _customersProductRepository.save(customersProduct);
    }

    @PostMapping(path = "/deleteCustomersProduct/{customerId}/{productId}")
    public void deleteCustomersProduct(@PathVariable("customerId") String customerId, @PathVariable("productId") long productId)
    {
        CustomersProduct customersProduct = new CustomersProduct(customerId, productId);
        _customersProductRepository.delete(customersProduct);
    }

    @GetMapping(path = "/getCustomersProductsByCustomerId")
    public List<CustomersProduct> getCustomersProductsByCustomerId(@RequestParam(value = "customerId") String customerId) {
        var customersProducts =  _customersProductRepository.findAllByCustomerId(customerId);
        return customersProducts.stream().distinct().toList();
    }


}
