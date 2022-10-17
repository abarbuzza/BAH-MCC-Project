package com.bah.spring.data.api;

import com.bah.spring.data.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CustomerApi {
    public ArrayList<Customer> customers = new ArrayList<Customer>();

    public CustomerApi() {
        customers.add(new Customer(2, "name", "11", "email"));
    }
    @GetMapping("/allCustomers")
    public ArrayList<Customer> getCustomers(){
        return customers;
    }

    @GetMapping("/getCustomerById/{id}")
    public Customer getCustomerById(@PathVariable("id") long id){
        for(Customer customer: customers){
            if(customer.getId() == id){
                return customer;
            }
        }
        return null;
    }
}