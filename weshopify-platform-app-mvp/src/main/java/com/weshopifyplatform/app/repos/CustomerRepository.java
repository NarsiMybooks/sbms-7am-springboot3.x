package com.weshopifyplatform.app.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.weshopifyplatform.app.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>, PagingAndSortingRepository<Customer, Integer> {

	@Query("from Customer c where c.userName=:userName")
	public Customer findCustomerByUserName(@Param("userName") String userName);
}
