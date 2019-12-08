package com.example.wbdvf19nubayserverjava.repositories;

import com.example.wbdvf19nubayserverjava.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends CrudRepository <Item, Integer> {

    // Create query for items of a certain keyword
    @Query(value = "SELECT * FROM items WHERE description LIKE :keyword or title LIKE :keyword",
    nativeQuery = true)
    public List<Item> findItemsByKeyword(@Param("keyword") String keyword);

    @Query(value = "SELECT COUNT(item_id) FROM bookmark_table WHERE item_id = :itemId",
            nativeQuery = true)
    public Integer findNumberOfBookmarksByItem(@Param("itemId") Integer itemId);

    @Query(value = "SELECT * FROM items where user_id = :userId",
            nativeQuery = true)
    public List<Item> findByUserid(@Param("userId") Integer userId);

    @Query(value = "SELECT * FROM items group by item_id order by item_id desc limit 5",
            nativeQuery = true)
    public List<Item> findFiveRecentItems();
}
