package com.example.wbdvf19nubayserverjava.controller;

import com.example.wbdvf19nubayserverjava.model.EbayItem;
import com.example.wbdvf19nubayserverjava.service.EbayItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class EbayItemController {
    private EbayItemService itemService;

    Logger logger = LoggerFactory.getLogger(EbayItemController.class);

    public EbayItemController() {
        this.itemService = new EbayItemService();
    }

    @GetMapping("/api/ebayItems/{keyword}")
    public List<EbayItem> findAllItemsKeyword(@PathVariable("keyword") String keyword) {
        logger.info("Keyword entered" + keyword);
        return this.itemService.findAllItemsKeyword(keyword);
    }
}
