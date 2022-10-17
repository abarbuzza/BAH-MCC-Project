package com.bah.spring.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.bah.spring.data.domain.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

}