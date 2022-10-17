package com.bah.spring.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.bah.spring.data.domain.Customer;

public interface CustomersRepository extends CrudRepository<Customer, Long> {

}