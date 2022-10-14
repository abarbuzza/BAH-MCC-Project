package com.bah.spring.Customer;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bah.spring.domain.CustomerObj;

@RestController
@RequestMapping("/allcustomers")
public class CustomerAPI {
    ArrayList<CustomerObj> customerList = new ArrayList<CustomerObj>();

    public CustomerAPI() {
        CustomerObj cus1 = new CustomerObj(1, "Anteneh", "antenehspswd", "anteneh@bah.com");
        CustomerObj cus2 = new CustomerObj(2, "Alex", "alexspswd", "alex@bah.com");
        CustomerObj cus3 = new CustomerObj(3, "Kierra", "kierrasspswd", "kierra@bah.com");
        CustomerObj cus4 = new CustomerObj(4, "Mick", "mickspswd", "mick@webage.com");
        CustomerObj cus5 = new CustomerObj(5, "Carlos", "carlospswd", "carlos@bah.com");
        CustomerObj cus6 = new CustomerObj(6, "Hampton", "hamptonpswd", "hampton@bah.com");
        CustomerObj cus7 = new CustomerObj(7, "Ahmed", "ahmedpswd", "ahmed@bah.com");
        CustomerObj cus8 = new CustomerObj(8, "Fox", "foxpswd", "foxisgreat@webage.com");

        customerList.add(cus1);
        customerList.add(cus2);
        customerList.add(cus3);
        customerList.add(cus4);
        customerList.add(cus5);
        customerList.add(cus6);
        customerList.add(cus7);
        customerList.add(cus8);
    }

    @GetMapping
    public Collection<CustomerObj> getAll() {
        return this.customerList;
    }

    @GetMapping("/{customerId}")
    public CustomerObj getCustomerById(@PathVariable("customerId") long id) {
        CustomerObj customer = null;
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId() == id) {
                customer = customerList.get(i);
            }
        }
        return customer;
    }

}