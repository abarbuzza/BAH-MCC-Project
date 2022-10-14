package com.bah.spring.Customer;

public class Customer {

    int Id;
    String name;
    String email;
    String userName;
    String password;

    public Customer(int id, String name, String email, String userName, String password) {
        Id = id;
        this.name = name;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
