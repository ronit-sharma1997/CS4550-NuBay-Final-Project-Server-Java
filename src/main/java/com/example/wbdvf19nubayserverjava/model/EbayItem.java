package com.example.wbdvf19nubayserverjava.model;

import java.util.List;

public class EbayItem {
    private String itemId;
    private String title;
    private String categoryName;
    private String ebayUrl;
    private List<String> imageUrl;
    private String location;
    private String value;
    private String shippingCost;
    private String conditionString;
    private String sellerId;

    public EbayItem() {

    }

    public EbayItem(String itemId, String title, String categoryName, String ebayUrl,
                    List<String> imageUrl, String location, String value, String shippingCost,
                    String conditionString, String sellerId) {
        this.itemId = itemId;
        this.title = title;
        this.categoryName = categoryName;
        this.ebayUrl = ebayUrl;
        this.imageUrl = imageUrl;
        this.location = location;
        this.value = value;
        this.shippingCost = shippingCost;
        this.conditionString = conditionString;
        this.sellerId = sellerId;
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

    public String getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(String shippingCost) {
        this.shippingCost = shippingCost;
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

    public List<String> getImageUrl() {
        return this.imageUrl;
    }

    public void setEbayUrl(String ebayUrl) {
        this.ebayUrl = ebayUrl;
    }

    public String getEbayUrl() {
        return ebayUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}