package com.example.wbdvf19nubayserverjava.model;

public class EbayItem {
    private String itemId;
    private String title;
    private String categoryName;
    private String viewItemURL;
    private String galleryURL;
    private String location;
    private String __value__;
    private String shippingCost;
    private String condition;
    private String sellerName;

    public EbayItem() {

    }

    public EbayItem(String itemId, String title, String categoryName, String viewItemURL,
                    String galleryURL, String location, String __value__, String shippingCost,
                    String condition, String sellerName) {
        this.itemId = itemId;
        this.title = title;
        this.categoryName = categoryName;
        this.viewItemURL = viewItemURL;
        this.galleryURL = galleryURL;
        this.location = location;
        this.__value__ = __value__;
        this.shippingCost = shippingCost;
        this.condition = condition;
        this.sellerName = sellerName;
    }


  public String getSellerName() {
    return sellerName;
  }

  public void setSellerName(String sellerName) {
    this.sellerName = sellerName;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
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

    public String getViewItemURL() {
        return viewItemURL;
    }

    public void setViewItemURL(String viewItemURL) {
        this.viewItemURL = viewItemURL;
    }

    public String getGalleryURL() {
        return galleryURL;
    }

    public void setGalleryURL(String galleryURL) {
        this.galleryURL = galleryURL;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String get__value__() {
        return __value__;
    }

    public void set__value__(String __value__) {
        this.__value__ = __value__;
    }
}
