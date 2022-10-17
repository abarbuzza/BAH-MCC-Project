package com.bah.spring.data.repo;

import com.bah.spring.data.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<Customer, Long> {
}
