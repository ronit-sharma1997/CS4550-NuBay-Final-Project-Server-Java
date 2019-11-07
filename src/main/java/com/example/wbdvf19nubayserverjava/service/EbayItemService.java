package com.example.wbdvf19nubayserverjava.service;

import com.example.wbdvf19nubayserverjava.model.Condition;
import com.example.wbdvf19nubayserverjava.model.EbayItem;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EbayItemService {
    private String ebayFindingAPI;
    private URL httpsGETRequest;

    public EbayItemService() {
        this.ebayFindingAPI = "https://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsByKeywords&SERVICE-VERSION=1.0.0&SECURITY-APPNAME=RonitSha-NuBay-PRD-4b31d5c2d-dcfa3e9a&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&outputSelector(0)=SellerInfo&keywords=";
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

    private void parseEbayJSON(List<EbayItem> response, URL httpsGETRequest) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonTree = mapper.readTree(httpsGETRequest);
        JsonNode itemTree = jsonTree.path("findItemsByKeywordsResponse").get(0).path("searchResult").get(0).path("item");
        for(JsonNode item : itemTree) {
            response.add(new EbayItem(item.path("itemId").get(0).asText(), item.path("title").get(0).asText(),
                    item.path("primaryCategory").get(0).path("categoryName").get(0).asText(), item.path("viewItemURL").get(0).asText(), item.path("galleryURL").get(0).asText(),
                    item.path("location").get(0).asText(),
                    item.path("sellingStatus").get(0).path("currentPrice").get(0).path("@currencyId").asText() + " " + item.path("sellingStatus").get(0).path("currentPrice").get(0).path("__value__").asText(), item.path("sellerInfo").get(0).path("sellerUserName").get(0).asText(), item.path("sellerInfo").get(0).path("positiveFeedbackPercent").get(0).asText()));
        }
    }

}
