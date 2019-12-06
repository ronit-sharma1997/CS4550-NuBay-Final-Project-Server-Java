package com.example.wbdvf19nubayserverjava.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name="users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName, lastName;
    private String username, password;
    private String email, phoneNumber;

    private typeOfUser userRole;

    @OneToMany (mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Item> items;

    public enum typeOfUser {
        BUYER,
        SELLER
    }

    public User() {
    }

    public User(String firstName, String lastName, String username, String password, String email, String phoneNumber, typeOfUser userRole, List<Item> items) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void set(User updatedUser) {
        this.email = updatedUser.getEmail();
        this.phoneNumber = updatedUser.getPhoneNumber();
        this.password = updatedUser.getPassword();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public typeOfUser getUserRole() {
        return userRole;
    }

    public void setUserRole(typeOfUser userRole) {
        this.userRole = userRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
