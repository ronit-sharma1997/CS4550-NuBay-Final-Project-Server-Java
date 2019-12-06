package com.example.wbdvf19nubayserverjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table (name = "serviceitems")
public class ServiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String categoryName;
    private String value; // hourly rate
    private String description;
    private String paymentOptions;
    private String location;
    private String availability;

    private String base64Image;

    @ManyToOne
    @JsonIgnore
    private User user;
    
    public ServiceItem () {
    }

    public ServiceItem (String title, String categoryName, String value, String description, String paymentOptions, String location, String availability, String base64Image, User user) {
        this.title = title;
        this.categoryName = categoryName;
        this.value = value;
        this.description = description;
        this.paymentOptions = paymentOptions;
        this.location = location;
        this.availability = availability;
        this.base64Image = base64Image;
        this.user = user;
    }

    public void set (ServiceItem updatedServiceItem) {
        this.title = updatedServiceItem.getTitle();
        this.categoryName = updatedServiceItem.getCategoryName();
        this.value = updatedServiceItem.getValue();
        this.description = updatedServiceItem.getDescription();
        this.paymentOptions = updatedServiceItem.getPaymentOptions();
        this.location = updatedServiceItem.getLocation();
        this.base64Image = updatedServiceItem.getBase64Image();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(String paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
