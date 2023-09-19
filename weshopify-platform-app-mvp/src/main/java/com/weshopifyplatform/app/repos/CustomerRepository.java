package com.weshopifyplatform.app.repos;

import org.springframework.data.repository.CrudRepository;

import com.weshopifyplatform.app.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
