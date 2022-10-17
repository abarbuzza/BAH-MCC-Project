package com.bah.spring.data.api;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEvent.getId()).toUri();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        for (Event ev:repo.findAll()){
            if(ev.getId() == newEvent.getId()){
                throw new Exception("Event already exists.");
            }
        }
        repo.save(newEvent);
        return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);    }

    @PutMapping("/{eventId}")
    public ResponseEntity<?> putEvent(
            @RequestBody Event newEvent,
            @PathVariable("eventId") long eventId) throws Exception {
        Event ev = repo.findById(eventId).orElseThrow(() -> new Exception("Event not found for this id: " + eventId));
        ev.setId(newEvent.getId());
        ev.setCode(newEvent.getCode());
        ev.setDescription(newEvent.getDescription());
        ev.setTitle(newEvent.getTitle());
        final Event updatedEvent = repo.save(newEvent);
        return ResponseEntity.ok(updatedEvent);
        //  Workshop:  Implement a method to update an entitye in response to a PUT message.
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> deleteEventById(@PathVariable("eventId") long id) throws Exception {
        //  Workshop:  Implement a method to delete an entity.
        Event ev = repo.findById(id).orElseThrow(() -> new Exception("Event not found with this event id: " + id));
        repo.delete(ev);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(ev);
    }

}