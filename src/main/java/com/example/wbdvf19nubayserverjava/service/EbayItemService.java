package com.example.wbdvf19nubayserverjava.service;

import com.example.wbdvf19nubayserverjava.model.EbayItem;
import com.example.wbdvf19nubayserverjava.model.DetailedEbayItem;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EbayItemService {
    private String ebayFindingAPI;
    private URL httpsGETRequest;
    private String shoppingApi;
    private String categoryApi;

    public EbayItemService() {
        this.ebayFindingAPI = "https://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=" +
                "findItemsByKeywords&SERVICE-VERSION=1.0.0&SECURITY-APPNAME=RonitSha-NuBay-PRD-4b31d5c2d-dcfa3e9a" +
                "&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&keywords=";
        this.shoppingApi = "http://open.api.ebay.com/shopping?callname=GetSingleItem&" +
                "responseencoding=JSON&version=967&IncludeSelector=details&appid=RonitSha-NuBay-PRD-4b31d5c2d-dcfa3e9a&" +
                "itemID=";
        this.categoryApi = "https://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsByCategory&SERVICE-VERSION=1.0.0&" +
                "SECURITY-APPNAME=RonitSha-NuBay-PRD-4b31d5c2d-dcfa3e9a&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&categoryId=";
    }

    public List<EbayItem> findAllItemsKeyword(String keyword) {
        List<EbayItem> response = new ArrayList<>();
        if(keyword == null || keyword.equals("")) {
            return response;
        }
        try {
            this.httpsGETRequest = new URL(this.ebayFindingAPI + keyword.replace(" ", "%20"));

            parseEbayJSON(response, this.httpsGETRequest);
        } catch (MalformedURLException m) {
            return response;
        } catch (IOException e) {
            return response;
        }

        return response;
    }

    public List<EbayItem> findAllItemsByCategoryId(String categoryId) {
      List<EbayItem> response = new ArrayList<>();
      if(categoryId == null || categoryId.equals("")) {
        return response;
      }
      try {
        this.httpsGETRequest = new URL(this.categoryApi + categoryId.replace(" ", "%20"));

        parseEbayJSON(response, this.httpsGETRequest);
      } catch (MalformedURLException m) {
        return response;
      } catch (IOException e) {
        return response;
      }
      return response;
    }

    public DetailedEbayItem getEbayItemById(String id) {
         DetailedEbayItem detailedEbayItem = null;
        try {
          this.httpsGETRequest = new URL(this.shoppingApi + id.replace(" ", "%20"));
          detailedEbayItem = this.parseEbayItemJSON(detailedEbayItem, httpsGETRequest);
        }
        catch (MalformedURLException m) {
            return null;
        }
        catch(IOException e) {
            return detailedEbayItem;
        }

        return detailedEbayItem;
    }

    private void parseEbayJSON(List<EbayItem> response, URL httpsGETRequest) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonTree = mapper.readTree(httpsGETRequest);
        JsonNode itemTree = jsonTree.path("findItemsByCategoryResponse").get(0).path("searchResult").get(0).path("item");
        for(JsonNode item : itemTree) {
            response.add(new EbayItem(item.path("itemId").get(0).asText(), item.path("title").get(0).asText(),
                    item.path("primaryCategory").get(0).path("categoryName").get(0).asText(), item.path("viewItemURL").get(0).asText(), item.path("galleryURL").get(0).asText(),
                    item.path("location").get(0).asText(),
                    item.path("sellingStatus").get(0).path("currentPrice").
                            get(0).path("@currencyId").asText() + " " +
                            item.path("sellingStatus").get(0).path("currentPrice").get(0).path("__value__").asText()));
        }
    }
    private DetailedEbayItem parseEbayItemJSON(DetailedEbayItem ebayItem, URL getRequest) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonTree = mapper.readTree(httpsGETRequest);
        JsonNode itemTree = jsonTree.path("Item");
        Integer a = 43;

       return new DetailedEbayItem(this.findPathNonArray(itemTree,"ItemID"),
               this.findPathNonArray(itemTree,"Title"),
                this.findPathNonArray(itemTree, "PrimaryCategoryName"),
                this.findPathNonArray(itemTree, "PrimaryCategoryID"),
                this.getImageList(itemTree),
                this.findPriceInfo(itemTree),
                this.findPathNonArray(itemTree, "Quantity"), this.findShippingInfo(itemTree),
                this.findPathNonArray(itemTree,"ViewItemURLForNaturalSearch"),
                itemTree.path("Seller").path("UserID").asText(),
                itemTree.path("Seller").path("PositiveFeedbackPercent").asText(),
               this.findPathNonArray(itemTree,"ConditionDisplayName"));
    }

    public List<String> getImageList(JsonNode itemTree) {
        List<String> imageUrls = new ArrayList<>();
        try {
           JsonNode nodes = itemTree.path("PictureURL");
            for(JsonNode image : nodes) {
               image.asText();
               imageUrls.add(image.asText());
            }

        }
        catch(Exception e) {
            return imageUrls;
        }
        return imageUrls;
    }

    private String findPathArray(JsonNode itemTree, String item) {
        try {
           return itemTree.path(item).get(0).asText();
        }
        catch(Exception e){
            return "";
        }
    }

    private String findShippingInfo(JsonNode itemTree) {
        try {
           return itemTree.path("shippingInfo").get(0).path("shippingServiceCost").get(0).path("@currencyId").asText() + " " +
                    itemTree.path("shippingInfo").get(0).path("currentPrice").get(0).path("__value__").asText();
        }
        catch(Exception e) {
            return "";
        }
    }

    private String findPathNonArray(JsonNode itemTree, String item) {
        try {
            return itemTree.path(item).asText();
        }
        catch(Exception e) {
            return "";
        }
    }

    private String findPriceInfo(JsonNode itemTree) {
       try {
           return itemTree.path("CurrentPrice").path("CurrencyID").asText() + " " +
                   itemTree.path("CurrentPrice").path("Value").asText();
       }
       catch(Exception e) {
           return "";
       }
    }


}


