package com.bah.spring.data.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.bah.spring.data.model.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationApi {
    ArrayList<Registration> registrationList = new ArrayList<Registration>();

    public RegistrationApi() {
        Registration reg1 = new Registration(1L, "1", "6", new Date(), "This is registration 1 for Customer 6, Hampton");
        Registration reg2 = new Registration(2L, "2", "8", new Date(), "This is registration 2 for Customer 8, Fox");
        Registration reg3 = new Registration(3L, "3", "2", new Date(), "This is registration 3 for Customer 2, Alex");
        Registration reg4 = new Registration(4L, "4", "4", new Date(), "This is registration 4 for Customer 4, Mick");
        Registration reg5 = new Registration(5L, "5", "1", new Date(), "This is registration 5 for Customer 1, Anteneh");


        registrationList.add(reg1);
        registrationList.add(reg2);
        registrationList.add(reg3);
        registrationList.add(reg4);
        registrationList.add(reg5);
    }

    @GetMapping("/allregistrations")
    public Collection<Registration> getAll() {
        return this.registrationList;
    }

    @GetMapping("/{registrationId}")
    public Registration getCustomerById(@PathVariable("registrationId") long id) {

        Registration registration = null;
        for (int i = 0; i < registrationList.size(); i++) {
            if (registrationList.get(i).getId() == id) {
                registration = registrationList.get(i);
            }
        }
        return registration;
    }

}