package com.example.wbdvf19nubayserverjava.model;

import javax.persistence.*;
import java.awt.*;
import java.util.List;

@Entity
@Table (name = "items")
public class Item {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private String itemId;

    private String title;
    private String categoryName;
    private String value;
    private String quantity;
    private String sellerId;
    private String conditionString;
    private String description;
    private String refundPolicy;
    private String paymentOptions;
    private String location;

    // handle image
//    @Lob
//    @Basic(fetch = FetchType.LAZY)
//    private byte[] itemPicture;

    private String base64Image;

    public Item () {
    }

    public Item(String title, String categoryName, String value, String quantity, String sellerId, String conditionString, String description, String refundPolicy, String paymentOptions, String location, String base64Image) {
        this.title = title;
        this.categoryName = categoryName;
        this.value = value;
        this.quantity = quantity;
        this.sellerId = sellerId;
        this.conditionString = conditionString;
        this.description = description;
        this.refundPolicy = refundPolicy;
        this.paymentOptions = paymentOptions;
        this.location = location;
        this.base64Image = base64Image;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
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

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
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
