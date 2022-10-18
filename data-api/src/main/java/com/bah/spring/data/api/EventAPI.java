package com.bah.spring.data.api;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.bah.spring.data.domain.Customer;
import com.bah.spring.data.domain.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.bah.spring.data.domain.Event;
import com.bah.spring.data.repository.EventRepository;

@RestController
@RequestMapping("/events")
public class EventAPI {
    @Autowired
    EventRepository repo;

    @GetMapping
    public Iterable<Event> getAll() {
        //  Workshop:  Implement a method to retrieve all events
        return repo.findAll();
    }

    @GetMapping("/{eventId}")
    public Optional<Event> getEventById(@PathVariable("eventId") long id) {
        //  Workshop:  Implement a method to retrieve a single event by it's ID
        return repo.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> addEvent(@RequestBody Event newEvent, UriComponentsBuilder uri) throws Exception {
        //  Workshop:  Implement a method to create a new event in response to a POST message.
        //  Think about how you ensure that the event is properly formed.
        if(newEvent.getCode() == null ||
                newEvent.getTitle() == null ||
                newEvent.getId() != 0 || newEvent.getDescription() == null){
            return ResponseEntity.badRequest().build();
        }
        newEvent = repo.save(newEvent);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newEvent.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<?> putEvent(
            @RequestBody Event newEvent,
            @PathVariable("eventId") long eventId) throws Exception {
        //  Workshop:  Write an implementation to update or create a new customer with an HTTP PUT, with the
        //  requestor specifying the customer ID.  Are there error conditions to be handled?  How much data
        //  validation should you implement considering that customers are stored in a CustomersRepository object.

        if (newEvent.getId() != eventId || newEvent.getDescription() == null || newEvent.getCode() == null || newEvent.getTitle() == null) {
            return ResponseEntity.badRequest().build();
        }
        repo.save(newEvent);
        return ResponseEntity.ok().build();
        //  Workshop:  Implement a method to update an entitye in response to a PUT message.
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> deleteEventById(@PathVariable("eventId") long id) throws Exception {
        //  Workshop:  Implement a method to delete an entity.

        for(Event e: this.getAll()){
            if(e.getId() == id){
                repo.delete(e);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().body("Event not found.");
    }

}