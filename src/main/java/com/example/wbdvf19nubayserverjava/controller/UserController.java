package com.example.wbdvf19nubayserverjava.controller;

import com.example.wbdvf19nubayserverjava.model.Item;
import com.example.wbdvf19nubayserverjava.model.User;
import com.example.wbdvf19nubayserverjava.repositories.ItemRepository;
import com.example.wbdvf19nubayserverjava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

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

    @PostMapping ("/api/users/{userId}/bookmarks/{itemId}")
    public Integer bookmarkItem(@PathVariable ("userId") Integer userId,
                            @PathVariable ("itemId") Integer itemId) {
        User user = userRepository.findById(userId).get();
        Item item = itemRepository.findById(itemId).get();
        user.addToBookmarks(item);
        userRepository.save(user);
        return 1;
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
}
