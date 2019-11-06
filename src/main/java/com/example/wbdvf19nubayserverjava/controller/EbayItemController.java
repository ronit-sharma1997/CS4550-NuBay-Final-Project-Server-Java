package com.example.wbdvf19nubayserverjava.controller;

import com.example.wbdvf19nubayserverjava.model.EbayItem;
import com.example.wbdvf19nubayserverjava.service.EbayItemService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EbayItemController {
    private EbayItemService itemService;

    public EbayItemController() {
        this.itemService = new EbayItemService();
    }

    @GetMapping("/api/ebayItems/{keyword}")
    public List<EbayItem> findAllItemsKeyword(@PathVariable("keyword") String keyword) {
        return this.itemService.findAllItemsKeyword(keyword);
    }
}
