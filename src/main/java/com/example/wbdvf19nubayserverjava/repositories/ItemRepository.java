package com.example.wbdvf19nubayserverjava.repositories;

import com.example.wbdvf19nubayserverjava.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends CrudRepository <Item, Integer> {

    // Create query for items of a certain keyword
    @Query(value = "SELECT * FROM cs4550_finalProjSchema.items WHERE description LIKE :keyword or title LIKE :keyword",
    nativeQuery = true)
    public List<Item> findItemsByKeyword(@Param("keyword") String keyword);
}
