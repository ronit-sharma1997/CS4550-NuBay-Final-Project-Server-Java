package com.example.wbdvf19nubayserverjava.controller;

import com.example.wbdvf19nubayserverjava.model.Item;
import com.example.wbdvf19nubayserverjava.repositories.ItemRepository;
import com.example.wbdvf19nubayserverjava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ItemController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

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

    @PostMapping ("/api/users/{userId}/items")
    public Item createItem
            (@PathVariable("userId") int userId,
             @RequestBody Item item) {
        item.setUser(userRepository.findById(userId).get());
        return itemRepository.save(item);
    }

    @GetMapping("/api/users/{userId}/items")
    public List<Item> findItemsByUserId(@PathVariable("userId") int userId) {
        return itemRepository.findByUserid(userId);
    }

    @GetMapping ("/api/searchitems/{keyword}")
    public List<Item> findItemsByKeyword (@PathVariable("keyword") String keyword) {
        keyword = "%" + keyword + "%";
        return itemRepository.findItemsByKeyword(keyword);
    }

    @DeleteMapping ("/api/items/{itemId}")
    public void deleteItemById (@PathVariable("itemId") int itemId) {
        itemRepository.deleteById(itemId);
    }

    @PutMapping("/api/items/{itemId}")
    public Item updateItem
            (@PathVariable ("itemId") Integer itemId,
             @RequestBody Item updatedItem) {
        Item item = itemRepository.findById(itemId).get();
        item.set(updatedItem);
        return itemRepository.save(item);
    }

    @GetMapping ("/api/items/{itemId}/bookmarks")
    public Integer findNumberOfBookmarksByItemId (@PathVariable("itemId") int itemId) {
        return itemRepository.findNumberOfBookmarksByItem(itemId);
    }
}
