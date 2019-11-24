package com.example.wbdvf19nubayserverjava.model;
import java.util.*;

public class DetailedEbayItem {

  private String itemId;
  private String title;
  private String categoryName;
  private String categoryId;
  private List<String> imageUrl;
  private String value;
  private String quantity;
  private String ebayUrl;
  private String shippingCost;
  private String sellerId;
  private String sellerRating;

public DetailedEbayItem() {

}

public DetailedEbayItem(String itemId, String title, String categoryName, String categoryId, List<String>
        imageUrl,
                 String value, String quanity,String shippingCost, String ebayUrl,
                        String sellerId, String sellerRating) {
      this.itemId = itemId;
      this.title = title;
      this.categoryName = categoryName;
      this.categoryId = categoryId;
      this.imageUrl = imageUrl;
      this.value = value;
      this.ebayUrl = ebayUrl;
      this.quantity = quanity;
      this.shippingCost = shippingCost;
      this.sellerId = sellerId;
      this.sellerRating = sellerRating;
}

  public String getSellerRating() {
    return sellerRating;
  }

  public void setSellerRating(String sellerRating) {
    this.sellerRating = sellerRating;
  }

  public String getSellerId() {
    return sellerId;
  }

  public void setSellerId(String sellerId) {
    this.sellerId = sellerId;
  }


  public String getShippingCost() {
    return shippingCost;
  }

  public void setShippingCost(String shippingCost) {
    this.shippingCost = shippingCost;
  }

  public String getEbayUrl() {
    return ebayUrl;
  }

  public void setEbayUrl(String ebayUrl) {
    this.ebayUrl = ebayUrl;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }


  public void setValue(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return this.title;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }
  public String getItemId() {
    return itemId;
  }

  public void setImageUrl(List<String> imageUrl) {
    this.imageUrl = imageUrl;
  }

  public List<String> getImageUrl() {
    return imageUrl;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }


}
