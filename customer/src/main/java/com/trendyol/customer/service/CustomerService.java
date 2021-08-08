package com.trendyol.customer.service;

import com.trendyol.customer.domain.Customer;
import com.trendyol.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository _customerRepository;

    public CustomerService(CustomerRepository customerRepository)
    {
        _customerRepository = customerRepository;
    }

    public Customer create(String tckn, String name, String surname, String email, String phoneNumber, String userName)
    {
        Customer customer = new Customer(tckn, name, surname, email, phoneNumber, userName);
        return _customerRepository.save(customer);
    }


}
