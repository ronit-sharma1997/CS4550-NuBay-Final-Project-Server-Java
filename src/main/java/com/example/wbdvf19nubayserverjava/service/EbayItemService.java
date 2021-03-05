package com.example.wbdvf19nubayserverjava.service;

import com.example.wbdvf19nubayserverjava.model.EbayItem;
import com.example.wbdvf19nubayserverjava.model.DetailedEbayItem;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.BreakIterator;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Locale;

public class EbayItemService {
    private String ebayFindingAPI;
    private URL httpsGETRequest;
    private String shoppingApi;
    private String categoryApi;

    public EbayItemService() {
        this.ebayFindingAPI = "https://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=" +
                "findItemsByKeywords&SERVICE-VERSION=1.0.0&SECURITY-APPNAME=RonitSha-NuBay-PRD-4b31d5c2d-dcfa3e9a" +
                "&RESPONSE-DATA-FORMAT=JSON&paginationInput.pageNumber=1&outputSelector(0)=SellerInfo&paginationInput.entriesPerPage=50&REST-PAYLOAD&keywords=";

        this.shoppingApi = "https://open.api.ebay.com/shopping?callname=GetSingleItem&" +
                "responseencoding=JSON&version=967&IncludeSelector=details," +
                "ShippingCosts,TextDescription&appid=RonitSha-NuBay-PRD-4b31d5c2d-dcfa3e9a&" +
                "itemID=";
        this.categoryApi = "https://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsByCategory&SERVICE-VERSION=1.0.0&" +
                "SECURITY-APPNAME=RonitSha-NuBay-PRD-4b31d5c2d-dcfa3e9a&outputSelector(0)=SellerInfo" +
                "&paginationInput.pageNumber=1" +
                "&paginationInput.entriesPerPage=12&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD" +
                "&categoryId=";
    }

    public List<EbayItem> findAllItemsKeyword(String keyword) {
        List<EbayItem> response = new ArrayList<>();
        if(keyword == null || keyword.equals("")) {
            return response;
        }
        try {
            this.httpsGETRequest = new URL(this.ebayFindingAPI + keyword.replace(" ",
                    "%20"));
            parseEbayJSON(response, this.httpsGETRequest,"findItemsByKeywordsResponse");
        } catch (MalformedURLException m) {
            return response;
        } catch (IOException e) {
            return response;
        }

        return response;
    }


    public List<EbayItem> getTrendingItems() {
      List<EbayItem> response = new ArrayList<>();
      String[] categoriesToGet = new String[]{"1059","15032","32852", "15273"};
      for(String category : categoriesToGet) {
        this.findNonDetailItemsByCategory(response,category);

      }
      return response;

    }


    private void parseEbayJSON(List<EbayItem> response, URL httpsGETRequest, String initialPath)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Set<String> alreadySeenIds = new HashSet<>();
        JsonNode jsonTree = mapper.readTree(httpsGETRequest);
        JsonNode itemTree = jsonTree.path(initialPath).get(0).path("searchResult").get(0).path("item");
        for(JsonNode item : itemTree) {
            List<String> images = new ArrayList<>();
            String id = item.path("itemId").get(0).asText();
            if(!alreadySeenIds.contains(id)) {
              alreadySeenIds.add(id);
              images.add(this.getGalleryUrlString(item));
              String shipping = this.getShippingCostItemTree(item);
              String condition = this.getConditionCostItemTree(item);
              String sellerName = this.getSellerUserName(item);
              response.add(new EbayItem(id, item.path("title").get(0).asText(),
                      item.path("primaryCategory").get(0).path("categoryName").get(0).asText(),
                      item.path("viewItemURL").get(0).asText(), images,
                      item.path("location").get(0).asText(),
                      item.path("sellingStatus").get(0).path("currentPrice").
                              get(0).path("@currencyId").asText() + " " +
                              item.path("sellingStatus").get(0).path("currentPrice").get(0).path("__value__").asText(),
                      shipping, condition, sellerName));
            }

        }
    }

    private String getGalleryUrlString(JsonNode item) {
      try {
       return item.path("galleryURL").get(0).asText();
      }
      catch (Exception e) {
        return "";
      }
    }

    public void getIdListFromResponse(List<String> allIds, URL httpsGETRequest) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonTree = mapper.readTree(httpsGETRequest);
        String categoryString4Later = "findItemsByCategoryResponse";
        JsonNode itemTree = jsonTree.path("findItemsByCategoryResponse").get(0).path("searchResult").get(0).path("item");
        for(JsonNode item : itemTree) {
            String id = item.path("itemId").get(0).asText();
            allIds.add(id);

        }
    }

    private void findNonDetailItemsByCategory(List<EbayItem> items,String categoryId) {
      try {
        this.httpsGETRequest =
                new URL(this.categoryApi + categoryId.replace(" ", "%20"));
        this.parseEbayJSON(items, this.httpsGETRequest,"findItemsByCategoryResponse");
      }
      catch (MalformedURLException m) {

      } catch (IOException e) {

      }



    }
    public List<DetailedEbayItem> findAllItemsByCategoryId(String categoryId) {
        List<DetailedEbayItem> response = new ArrayList<>();
        List<String> idList = new ArrayList<>();
        if(categoryId == null || categoryId.equals("")) {
            return response;
        }
        try {
            this.httpsGETRequest = new URL(this.categoryApi + categoryId.replace(" ", "%20"));
            this.getIdListFromResponse(idList, httpsGETRequest);
            for(String id : idList) {
                DetailedEbayItem currItem =  this.getEbayItemById(id);
                response.add(currItem);
            }
            //parseEbayJSON(response, this.httpsGETRequest);
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

    private String getConditionCostItemTree(JsonNode itemTree) {
        try {
            return itemTree.path("condition").get(0).path("conditionDisplayName").get(0).asText();
        }
        catch(Exception e) {
            return  "";
        }


    }

    private String parseTextDesctiption(JsonNode itemTree) {
        try {
            String fullDescription = itemTree.path("Description").asText();
            BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.ENGLISH);
            iterator.setText(fullDescription);
            int numSentences = 0;
            int end = 0;
            int start = iterator.first();
            iterator.next();
            end = iterator.next();

            return fullDescription.substring(start,end);

        }

        catch (Exception e) {
            return "";
        }

    }


    private String getRefundPolicy(JsonNode itemTree) {


        try {
            String returnsAccepted = itemTree.path("ReturnPolicy").path("ReturnsAccepted").asText();
            if(returnsAccepted.equals("Returns Accepted")) {
                String refundType = itemTree.path("ReturnPolicy").path("Refund").asText();
                String dayLimit = itemTree.path("ReturnPolicy").path("ReturnsWithin").asText();
                return refundType + " within " + dayLimit;

            }
            else {
                return "Non refundable";
            }
        }

        catch (Exception e) {
            return "";
        }

    }

    private String getPaymentOptions(JsonNode itemTree) {
        String paymentMethods = "";

        try {
            JsonNode j = itemTree.path("PaymentMethods");
            j.size();
            for(JsonNode payment : j) {
            for(int i = 0; i < j.size(); i ++) {
              JsonNode currPayment = j.get(i);
              if(i == j.size() - 1) {
                paymentMethods += (payment.asText());
              }
              else {
                paymentMethods += (payment.asText());
                paymentMethods += ",";
              }
            }

            }
            return paymentMethods;
        }

        catch(Exception e) {
            return paymentMethods;
        }
    }

    private String getLocation(JsonNode itemTree) {
        try {
            return itemTree.path("Location").asText();
        }
        catch(Exception e) {
            return "";
        }

    }


    private String getShippingCostItemTree(JsonNode itemTree) {
        try {
            JsonNode shippingInfo =  itemTree.path("shippingInfo").get(0).path("shippingServiceCost").get(0);
            return shippingInfo.get("@currencyId").asText() + " " + shippingInfo.get("__value__").asText();
        }
        catch(Exception e) {
            return "";
        }

    }




    private DetailedEbayItem parseEbayItemJSON(DetailedEbayItem ebayItem, URL getRequest) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonTree = mapper.readTree(httpsGETRequest);
        JsonNode itemTree = jsonTree.path("Item");
        String mainDescription = this.parseTextDesctiption(itemTree);
        String refundPolicy = this.getRefundPolicy(itemTree);
        String paymentMethods = this.getPaymentOptions(itemTree);
        String location = this.getLocation(itemTree);
        return new DetailedEbayItem(this.findPathNonArray(itemTree,"ItemID"),
                this.findPathNonArray(itemTree,"Title"),
                this.findPathNonArray(itemTree, "PrimaryCategoryName"),
                this.findPathNonArray(itemTree, "PrimaryCategoryID"),
                this.getImageList(itemTree),
                this.findPriceInfo(itemTree),
                this.findPathNonArray(itemTree, "Quantity"),
                this.detailedShippingCost(itemTree),
                this.findPathNonArray(itemTree,"ViewItemURLForNaturalSearch"),
                itemTree.path("Seller").path("UserID").asText(),
                itemTree.path("Seller").path("PositiveFeedbackPercent").asText(),
                this.findPathNonArray(itemTree,"ConditionDisplayName"),mainDescription,refundPolicy,
                paymentMethods,location);
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

    private String detailedShippingCost(JsonNode itemTree) {
        try {
            String s =  itemTree.path("ShippingCostSummary").path("ListedShippingServiceCost").path("Value").asText();
            return itemTree.path("ListedShippingServiceCost").path("Value").asText();
        }
        catch(Exception e) {
            return "";
        }

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

    private String getSellerUserName(JsonNode itemTree) {
        try {
            return itemTree.path("sellerInfo").get(0).path("sellerUserName").get(0).asText();
        }
        catch (Exception e) {
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
