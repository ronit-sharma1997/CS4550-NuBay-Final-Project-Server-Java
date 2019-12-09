package com.example.wbdvf19nubayserverjava.repositories;

import com.example.wbdvf19nubayserverjava.model.ServiceItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceItemRepository extends CrudRepository <ServiceItem, Integer> {

    // Create query for serviceItems of a certain keyword
    @Query(value = "SELECT * FROM serviceitems WHERE description LIKE :keyword or title LIKE :keyword",
            nativeQuery = true)
    public List<ServiceItem> findServiceItemsByKeyword(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM serviceitems group by id order by id desc limit 5",
            nativeQuery = true)
    public List<ServiceItem> findFiveRecentServiceItems();

    @Query(value = "SELECT * FROM serviceitems WHERE category_name LIKE :categoryName",
            nativeQuery = true)
    public List<ServiceItem> findServiceItemsByCategory(@Param("categoryName") String categoryName);
}
