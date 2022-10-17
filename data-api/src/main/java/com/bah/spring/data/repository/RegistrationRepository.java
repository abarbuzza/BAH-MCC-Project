package com.bah.spring.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.bah.spring.data.domain.Registration;

public interface RegistrationRepository extends CrudRepository<Registration, Long>{

}