package com.trendyol.customer.repository;

import com.trendyol.customer.domain.Customer;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CouchbaseRepository<Customer, String> {
}
