package com.example.wbdvf19nubayserverjava.repositories;

import com.example.wbdvf19nubayserverjava.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository <User, Integer> {

//    @Query(
//            value = "SELECT COUNT (*) from users u where u.username = :username",
//            nativeQuery = true
//    )
//    public Integer findUserByUsername (@Param("username") String username);

    @Query("SELECT user FROM User user WHERE user.username=:username")
    public List<User> findUserByUsername
            (@Param("username") String username);

    @Query("SELECT user from User user WHERE user.username=:username AND user.password=:password")
    public List<User> findUserByCredential
            (@Param("username") String username, @Param("password") String password);
}
