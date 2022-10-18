package com.bah.spring.data.api;

import java.net.URI;
import java.util.*;

import com.bah.spring.data.domain.Event;
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

import com.bah.spring.data.domain.Registration;
import com.bah.spring.data.repository.RegistrationRepository;

@RestController
@RequestMapping("/registrations")
public class RegistrationAPI {

    @Autowired
    RegistrationRepository repo;

    @GetMapping
    public Iterable<Registration> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{registrationId}")
    public Optional<Registration> getRegistrationById(@PathVariable("registrationId") long id) {
        return repo.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> addRegistration(@RequestBody Registration newRegistration, UriComponentsBuilder uri) throws Exception {

        if(newRegistration.getRegistration_date() == null ||
                newRegistration.getNotes() == null ||
                newRegistration.getId() != 0 || newRegistration.getCustomer_id() == null || newRegistration.getEvent_id() == null){
            return ResponseEntity.badRequest().build();
        }
        newRegistration = repo.save(newRegistration);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newRegistration.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<?> putRegistration(
            @RequestBody Registration newRegistration,
            @PathVariable("eventId") long eventId) throws Exception
    {
        if(newRegistration.getRegistration_date() == null ||
                newRegistration.getNotes() == null ||
                newRegistration.getId() == 0 || newRegistration.getCustomer_id() == null || newRegistration.getEvent_id() == null){
            return ResponseEntity.badRequest().build();
        }
        repo.save(newRegistration);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> deleteRegistrationById(@PathVariable("eventId") long id) throws Exception {
        for(Registration r: this.getAll()){
            if(r.getId() == id){
                repo.delete(r);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().body("Event not found.");
    }

}