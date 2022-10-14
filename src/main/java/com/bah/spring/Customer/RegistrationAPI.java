package com.bah.spring.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bah.spring.domain.RegistrationObj;

@RestController
@RequestMapping("/allregistrations")
public class RegistrationAPI {
    ArrayList<RegistrationObj> registrationList = new ArrayList<RegistrationObj>();

    @SuppressWarnings("deprecation")
    public RegistrationAPI() {
        RegistrationObj reg1 = new RegistrationObj(1L, "1", "6", new Date(), "This is registration 1 for Customer 6, Hampton");
        RegistrationObj reg2 = new RegistrationObj(2L, "2", "8", new Date(), "This is registration 2 for Customer 8, Fox");
        RegistrationObj reg3 = new RegistrationObj(3L, "3", "2", new Date(), "This is registration 3 for Customer 2, Alex");
        RegistrationObj reg4 = new RegistrationObj(4L, "4", "4", new Date(), "This is registration 4 for Customer 4, Mick");
        RegistrationObj reg5 = new RegistrationObj(5L, "5", "1", new Date(), "This is registration 5 for Customer 1, Anteneh");


        registrationList.add(reg1);
        registrationList.add(reg2);
        registrationList.add(reg3);
        registrationList.add(reg4);
        registrationList.add(reg5);
    }

    @GetMapping
    public Collection<RegistrationObj> getAll() {
        return this.registrationList;
    }

    @GetMapping("/{registrationId}")
    public RegistrationObj getCustomerById(@PathVariable("registrationId") long id) {

        RegistrationObj registration = null;
        for (int i = 0; i < registrationList.size(); i++) {
            if (registrationList.get(i).getId() == id) {
                registration = registrationList.get(i);
            }
        }
        return registration;
    }

}