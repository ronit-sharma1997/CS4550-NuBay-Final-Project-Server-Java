package com.example.wbdvf19nubayserverjava.controller;

import com.example.wbdvf19nubayserverjava.model.Item;
import com.example.wbdvf19nubayserverjava.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ItemController {
    @Autowired
    ItemRepository itemRepository;

    @GetMapping ("/api/items/{itemId}/image")
    public String findImageByItemId (@PathVariable("itemId") int itemId) {
        Item item = itemRepository.findById(itemId).get();
        return item.getBase64Image();
    }

    @GetMapping ("/api/items")
    public List<Item> findAllItems () {
        return (List<Item>) itemRepository.findAll();
    }

    @GetMapping ("/api/items/{itemId}")
    public Item findItemById (@PathVariable("itemId") int itemId) {
        return itemRepository.findById(itemId).get();
    }

    @PostMapping ("/api/items")
    public Item createItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @GetMapping ("/api/searchitems/{keyword}")
    public List<Item> findItemsByKeyword (@PathVariable("keyword") String keyword) {
        keyword = "%" + keyword + "%";
        return itemRepository.findItemsByKeyword(keyword);
    }

}
