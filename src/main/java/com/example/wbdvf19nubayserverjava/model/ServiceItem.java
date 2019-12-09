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

    private Integer seller_id;
    private String seller_name;

    @Lob
    private byte[] image1;
    @Lob
    private byte[] image2;
    @Lob
    private byte[] image3;


    @ManyToOne
    @JsonIgnore
    private User user;
    
    public ServiceItem () {
    }

    public ServiceItem (String title, String categoryName, String value, String description,
                        String paymentOptions, String location, String availability,byte[] image1,
            byte[] image2, byte[] image3,User user) {
        this.title = title;
        this.categoryName = categoryName;
        this.value = value;
        this.description = description;
        this.paymentOptions = paymentOptions;
        this.location = location;
        this.availability = availability;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.user = user;
    }

    public void set (ServiceItem updatedServiceItem) {
        this.title = updatedServiceItem.getTitle();
        this.categoryName = updatedServiceItem.getCategoryName();
        this.value = updatedServiceItem.getValue();
        this.description = updatedServiceItem.getDescription();
        this.availability = updatedServiceItem.getAvailability();
        this.paymentOptions = updatedServiceItem.getPaymentOptions();
        this.location = updatedServiceItem.getLocation();
        this.image1 = updatedServiceItem.getImage1();
        this.image2 = updatedServiceItem.getImage2();
        this.image3 = updatedServiceItem.getImage3();
    }

    public Integer getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Integer seller_id) {
        this.seller_id = seller_id;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
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

    public byte[] getImage3() {
        return image3;
    }

    public byte[] getImage2() {
        return image2;
    }

    public byte[] getImage1() {
        return image1;
    }

    public void setImage3(byte[] image3) {
        this.image3 = image3;
    }

    public void setImage2(byte[] image2) {
        this.image2 = image2;
    }

    public void setImage1(byte[] image1) {
        this.image1 = image1;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
