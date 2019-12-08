package com.example.wbdvf19nubayserverjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.awt.*;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "items")
public class Item {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer itemId;

    private String title;
    private String categoryName;
    private String value;
    private String quantity;

    private String conditionString;
    private String description;
    private String refundPolicy;
    private String paymentOptions;
    private String location;

    // handle image
//    @Lob
//    @Basic(fetch = FetchType.LAZY)
//    private byte[] itemPicture;

    @Lob
    private byte[] image1;

    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToMany (mappedBy = "bookmarkedItems")
    private Set<User> bookmarks;

    public Item () {
    }

    public Item(String title, String categoryName, String value, String
            quantity, String conditionString, String description, String refundPolicy,
                String paymentOptions, String location, byte[] base64Image, User user) {
        this.title = title;
        this.categoryName = categoryName;
        this.value = value;
        this.quantity = quantity;
        this.conditionString = conditionString;
        this.description = description;
        this.refundPolicy = refundPolicy;
        this.paymentOptions = paymentOptions;
        this.location = location;
        this.image1 = base64Image;
        this.user = user;
    }

    public void set (Item updatedItem) {
        this.title = updatedItem.getTitle();
        this.categoryName = updatedItem.getCategoryName();
        this.value = updatedItem.getValue();
        this.quantity = updatedItem.getQuantity();
        this.conditionString = updatedItem.getConditionString();
        this.description = updatedItem.getDescription();
        this.refundPolicy = updatedItem.getRefundPolicy();
        this.paymentOptions = updatedItem.getPaymentOptions();
        this.location = updatedItem.getLocation();
        this.image1 = updatedItem.getImage1();
    }

    // override equals method
    public boolean equals(Object obj){
        if (obj instanceof Item) {
            Item itemToTest = (Item) obj;
            return (itemToTest.getItemId().equals(this.getItemId()));
        } else {
            return false;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

  public byte[] getImage1() {
    return image1;
  }

  public void setImage1(byte[] image1) {
    this.image1 = image1;
  }

  public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getConditionString() {
        return conditionString;
    }

    public void setConditionString(String conditionString) {
        this.conditionString = conditionString;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRefundPolicy() {
        return refundPolicy;
    }

    public void setRefundPolicy(String refundPolicy) {
        this.refundPolicy = refundPolicy;
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
}
