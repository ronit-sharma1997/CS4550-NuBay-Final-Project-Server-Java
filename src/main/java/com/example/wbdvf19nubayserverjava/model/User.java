package com.example.wbdvf19nubayserverjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Entity
@Table (name="users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName, lastName;
    private String username, password;
    private String email, phoneNumber;

    private String bookmarkedEbayItems;

    private typeOfUser userRole;

    @OneToMany (mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Item> items;

    @OneToMany (mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<ServiceItem> serviceItems;

    @ManyToMany
    @JoinTable(
            name = "bookmark_table",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> bookmarkedItems;

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

    public User(String firstName, String lastName, String username, String password, String email, String phoneNumber, String bookmarkedEbayItems, typeOfUser userRole, List<Item> items, List<ServiceItem> serviceItems, Set<Item> bookmarkedItems) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bookmarkedEbayItems = bookmarkedEbayItems;
        this.userRole = userRole;
        this.items = items;
        this.serviceItems = serviceItems;
        this.bookmarkedItems = bookmarkedItems;
    }

    public void addToBookmarkedEbayItems(String ebayItemId) {
        if (this.bookmarkedEbayItems == null || this.bookmarkedEbayItems.length() == 0) {
            this.bookmarkedEbayItems = ebayItemId;
        }
        else {
            this.bookmarkedEbayItems = this.bookmarkedEbayItems.concat("," + ebayItemId);
        }
    }

    public void removeFromBookmarkedEbayItems(String ebayItemId) {
        ArrayList<String> bookmarkedEbayItemsList = new ArrayList<String>(Arrays.asList(this.bookmarkedEbayItems.split(",")));
        int indexToRemove = -1;
        for (int i = 0; i < bookmarkedEbayItemsList.size(); i++) {
            if (bookmarkedEbayItemsList.get(i).equals(ebayItemId)) {
                indexToRemove = i;
            }
        }
        if (indexToRemove != -1) {
            bookmarkedEbayItemsList.remove(indexToRemove);
            this.bookmarkedEbayItems = String.join(",", bookmarkedEbayItemsList);
        }
    }

    public void addToBookmarks(Item item) {
        this.bookmarkedItems.add(item);
    }

    public void removeFromBookmarks(Item item) {
        this.bookmarkedItems.remove(item);
    }

    public String getBookmarkedEbayItems() {
        return bookmarkedEbayItems;
    }

    public void setBookmarkedEbayItems(String bookmarkedEbayItems) {
        this.bookmarkedEbayItems = bookmarkedEbayItems;
    }

    public List<ServiceItem> getServiceItems() {
        return serviceItems;
    }

    public void setServiceItems(List<ServiceItem> serviceItems) {
        this.serviceItems = serviceItems;
    }

    public Set<Item> getBookmarkedItems() {
        return bookmarkedItems;
    }

    public void setBookmarkedItems(Set<Item> bookmarkedItems) {
        this.bookmarkedItems = bookmarkedItems;
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
