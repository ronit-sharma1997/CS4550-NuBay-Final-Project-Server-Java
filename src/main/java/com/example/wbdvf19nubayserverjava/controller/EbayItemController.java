package com.example.wbdvf19nubayserverjava.controller;

import com.example.wbdvf19nubayserverjava.model.DetailedEbayItem;
import com.example.wbdvf19nubayserverjava.model.EbayItem;
import com.example.wbdvf19nubayserverjava.service.EbayItemService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class EbayItemController {
    private EbayItemService itemService;

    public EbayItemController() {
        this.itemService = new EbayItemService();
    }

    @GetMapping("/api/ebayItems/{keyword}")
    public List<EbayItem> findAllItemsKeyword(@PathVariable("keyword") String keyword) {
        return this.itemService.findAllItemsKeyword(keyword);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/api/ebayItem/{itemid}")
    public DetailedEbayItem findItemByEbayId(@PathVariable("itemid") String id) {
        return itemService.getEbayItemById(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/api/ebayItems/trending")
    public List<EbayItem> getTrendingItems() {
        return itemService.getTrendingItems();

    }

    @CrossOrigin(origins = "*")
    @GetMapping("/api/ebayCategories/{categoryId}")
    public List<DetailedEbayItem> findItemsById(@PathVariable("categoryId") String catId) {
        //return new ArrayList<>();
        return itemService.findAllItemsByCategoryId(catId);
    }

}