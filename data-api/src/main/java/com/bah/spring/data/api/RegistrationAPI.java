package com.bah.spring.data.api;

import java.net.URI;
import java.util.*;

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
import org.springframework.web.util.UriComponents;
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
        URI location = uri.build(newRegistration);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        for (Registration reg:repo.findAll()){
            if(reg.getId() == newRegistration.getId()){
                throw new Exception("Registration already exists.");
            }
        }
        repo.save(newRegistration);
        return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<?> putRegistration(
            @RequestBody Registration newRegistration,
            @PathVariable("eventId") long eventId) throws Exception
    {
        Registration reg = repo.findById(eventId).orElseThrow(() -> new Exception("Registration not found for this event id: " + eventId));
        reg.setEvent_id(newRegistration.getEvent_id());
        reg.setCustomer_id(newRegistration.getCustomer_id());
        reg.setRegistration_date(newRegistration.getRegistration_date());
        reg.setNotes(newRegistration.getNotes());
        final Registration updatedRegistration = repo.save(reg);
        return ResponseEntity.ok(updatedRegistration);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> deleteRegistrationById(@PathVariable("eventId") long id) throws Exception {
        Registration reg = repo.findById(id).orElseThrow(() -> new Exception("Registration not found for this event id: \" + eventId"));
        repo.delete(reg);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(reg);
    }

}