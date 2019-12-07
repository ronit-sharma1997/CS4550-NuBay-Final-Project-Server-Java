package com.example.wbdvf19nubayserverjava.controller;

import com.example.wbdvf19nubayserverjava.model.ServiceItem;
import com.example.wbdvf19nubayserverjava.repositories.ServiceItemRepository;
import com.example.wbdvf19nubayserverjava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ServiceItemController {
    @Autowired
    ServiceItemRepository serviceItemRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/api/serviceitems/{itemId}/image")
    public String findImageByServiceItemId (@PathVariable("itemId") int itemId) {
        ServiceItem serviceItem = serviceItemRepository.findById(itemId).get();
        return serviceItem.getBase64Image();
    }

    @GetMapping ("/api/serviceitems")
    public List<ServiceItem> findAllServiceItems () {
        return (List<ServiceItem>) serviceItemRepository.findAll();
    }

    @GetMapping ("/api/serviceitems/{itemId}")
    public ServiceItem findServiceItemById (@PathVariable("itemId") int itemId) {
        return serviceItemRepository.findById(itemId).get();
    }

    @PostMapping("/api/users/{userId}/serviceitems")
    public ServiceItem createServiceItem
            (@PathVariable("userId") int userId,
             @RequestBody ServiceItem serviceItem) {
        serviceItem.setUser(userRepository.findById(userId).get());
        return serviceItemRepository.save(serviceItem);
    }

    @GetMapping ("/api/searchserviceitems/{keyword}")
    public List<ServiceItem> findServiceItemsByKeyword (@PathVariable("keyword") String keyword) {
        keyword = "%" + keyword + "%";
        return serviceItemRepository.findServiceItemsByKeyword(keyword);
    }

    @DeleteMapping ("/api/serviceitems/{itemId}")
    public void deleteServiceItemById (@PathVariable("itemId") int itemId) {
        serviceItemRepository.deleteById(itemId);
    }

    @PutMapping("/api/serviceitems/{itemId}")
    public ServiceItem updateServiceItem
            (@PathVariable ("itemId") Integer itemId,
             @RequestBody ServiceItem updatedServiceItem) {
        ServiceItem serviceItem = serviceItemRepository.findById(itemId).get();
        serviceItem.set(updatedServiceItem);
        return serviceItemRepository.save(serviceItem);
    }
}
