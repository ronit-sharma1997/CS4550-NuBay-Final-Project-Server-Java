package com.example.wbdvf19nubayserverjava.controller;

import com.example.wbdvf19nubayserverjava.model.DetailedEbayItem;
import com.example.wbdvf19nubayserverjava.model.Item;
import com.example.wbdvf19nubayserverjava.model.User;
import com.example.wbdvf19nubayserverjava.repositories.ItemRepository;
import com.example.wbdvf19nubayserverjava.repositories.UserRepository;
import com.example.wbdvf19nubayserverjava.service.EbayItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    private EbayItemService ebayItemService;

    public UserController() {
        this.ebayItemService = new EbayItemService();
    }

    @GetMapping("/api/users/{userId}")
    public User findUserById
            (@PathVariable("userId") int userId) {
        return userRepository.findById(userId).get();
    }

    @GetMapping("/api/users")
    public List<User> findAllUsers () {
        return (List<User>) userRepository.findAll();
    }

//    @GetMapping("/api/users/{username}")
//    public List<User> findUserByUsername
//            (@PathVariable("username") String username) {
//        return userRepository.findUserByUsername(username);
//    }

    // Return an integer depending on result
    // -2 = Seller email does not end with correct suffix
    // -1 = Username already exists
    // Other (new user id) = Created user successfully
    @PostMapping("/api/register")
    public Integer createUser (@RequestBody User user) {
        Integer newUserId;

        // If you want to create a SELLER and the seller's email does NOT end with the husky email
        if (user.getUserRole() == User.typeOfUser.SELLER && !user.getEmail().endsWith("@husky.neu.edu")) {
            newUserId = -2;
            return newUserId;
        }

        List<User> userList = userRepository.findUserByUsername(user.getUsername());
        // User already exists with this username
        if (userList.size() == 1) {
            newUserId = -1;
        }
        else {
            User newUser = userRepository.save(user);
            newUserId = newUser.getId();
        }
        return newUserId;
    }

    // Return an integer depending on result
    // -1 = Incorrect credentials
    // Other (user id) = Correct credentials
    @PostMapping("/api/login")
    public Integer loginUser (@RequestBody User user) {
        Integer loginUserId;
        List<User> userList = userRepository.findUserByCredential(user.getUsername(), user.getPassword());

        // Credentials are correct
        if (userList.size() == 1) {
            loginUserId = userList.get(0).getId();
        }
        else {
            loginUserId = -1;
        }
        return loginUserId;
    }

    @PutMapping("/api/users/{userId}")
    public User updateUser
            (@PathVariable ("userId") Integer userId,
             @RequestBody User updatedUser) {
        User user = userRepository.findById(userId).get();
        user.set(updatedUser);
        return userRepository.save(user);
    }

    // Return 0 if success
    @PostMapping ("/api/users/{userId}/bookmarks/{itemId}")
    public Integer bookmarkItem(@PathVariable ("userId") Integer userId,
                            @PathVariable ("itemId") Integer itemId) {
        User user = userRepository.findById(userId).get();
        Item item = itemRepository.findById(itemId).get();
        user.addToBookmarks(item);
        userRepository.save(user);
        return 0;
    }

    @GetMapping ("/api/users/{userId}/bookmarks")
    public List<Item> getBookmarkedItems(@PathVariable("userId") Integer userId) {
        List<Integer> itemIdList = userRepository.findBookmarkedItemIdsByUser(userId);
        List<Item> bookmarkedItemList = new ArrayList<>();
        for (Integer i : itemIdList) {
            bookmarkedItemList.add(itemRepository.findById(i).get());
        }
        return bookmarkedItemList;
    }

    @GetMapping ("/api/users/{userId}/recentbookmarks")
    public List<Item> getRecentBookmarkedItems(@PathVariable("userId") Integer userId) {
        List<Integer> itemIdList = userRepository.findBookmarkedItemIdsByUser(userId);
        List<Item> bookmarkedItemList = new ArrayList<>();
        for (Integer i : itemIdList) {
            bookmarkedItemList.add(itemRepository.findById(i).get());
            // return only 5 bookmarks
            if (bookmarkedItemList.size() == 5) {
                break;
            }
        }
        return bookmarkedItemList;
    }

    // Return 0 if success
    @DeleteMapping ("/api/users/{userId}/bookmarks/{itemId}")
    public Integer deleteBookmarkedItem(@PathVariable ("userId") Integer userId,
                                @PathVariable ("itemId") Integer itemId) {
        User user = userRepository.findById(userId).get();
        Item item = itemRepository.findById(itemId).get();

        user.removeFromBookmarks(item);
        userRepository.save(user);
        return 0;
    }

    @PostMapping ("/api/users/{userId}/ebaybookmarks/{itemId}")
    public String addBookmarkedEbayItem
            (@PathVariable ("userId") Integer userId,
             @PathVariable ("itemId") String itemId) {
        User user = userRepository.findById(userId).get();
        user.addToBookmarkedEbayItems(itemId);
        userRepository.save(user);
        return userRepository.findById(userId).get().getBookmarkedEbayItems();
    }

    @GetMapping ("/api/users/{userId}/ebaybookmarks")
    public List<DetailedEbayItem> getEbayBookmarks (@PathVariable ("userId") Integer userId) {
        User user = userRepository.findById(userId).get();

        List<DetailedEbayItem> detailedEbayItemList = new ArrayList<>();
        ArrayList<String> bookmarkedEbayItemsList = new ArrayList<String>(Arrays.asList(user.getBookmarkedEbayItems().split(",")));
        for (String ebayItemId : bookmarkedEbayItemsList) {
            detailedEbayItemList.add(this.ebayItemService.getEbayItemById(ebayItemId));
        }
        return detailedEbayItemList;
    }

    @DeleteMapping ("/api/users/{userId}/ebaybookmarks/{itemId}")
    public String deleteEbayBookmark
            (@PathVariable ("userId") Integer userId,
             @PathVariable ("itemId") String itemId) {
        User user = userRepository.findById(userId).get();
        user.removeFromBookmarkedEbayItems(itemId);
        userRepository.save(user);
        return userRepository.findById(userId).get().getBookmarkedEbayItems();
    }
}
