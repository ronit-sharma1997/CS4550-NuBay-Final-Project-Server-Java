package com.example.wbdvf19nubayserverjava.repositories;

import com.example.wbdvf19nubayserverjava.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository <User, Integer> {

    @Query("SELECT user FROM User user WHERE user.username=:username")
    public List<User> findUserByUsername
            (@Param("username") String username);

    @Query("SELECT user from User user WHERE user.username=:username AND user.password=:password")
    public List<User> findUserByCredential
            (@Param("username") String username, @Param("password") String password);

    @Query(value = "SELECT item_id FROM bookmark_table WHERE user_id = :userId",
            nativeQuery = true)
    public List<Integer> findBookmarkedItemIdsByUser(@Param("userId") Integer userId);
}
