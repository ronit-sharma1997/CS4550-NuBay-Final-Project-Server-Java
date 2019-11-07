package com.example.wbdvf19nubayserverjava.model;

public class EbayItem {
    private String itemId;
    private String title;
    private String categoryName;
    private String viewItemURL;
    private String galleryURL;
    private String location;
    private String price;
    private Condition condition;
    private String seller;
    private String sellerRating;

    public EbayItem() {

    }

    public EbayItem(String itemId, String title, String categoryName, String viewItemURL, String galleryURL, String location, String price, Condition condition, String seller, String sellerRating) {
        this.itemId = itemId;
        this.title = title;
        this.categoryName = categoryName;
        this.viewItemURL = viewItemURL;
        this.galleryURL = galleryURL;
        this.location = location;
        this.price = price;
        this.condition = condition;
        this.seller = seller;
        this.sellerRating = sellerRating;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSellerRating() {
        return sellerRating;
    }

    public void setSellerRating(String sellerRating) {
        this.sellerRating = sellerRating;
    }
}
