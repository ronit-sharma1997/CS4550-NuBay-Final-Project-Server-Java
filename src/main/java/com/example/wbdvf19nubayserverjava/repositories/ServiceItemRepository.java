package com.example.wbdvf19nubayserverjava.repositories;

import com.example.wbdvf19nubayserverjava.model.ServiceItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceItemRepository extends CrudRepository <ServiceItem, Integer> {

    // Create query for serviceItems of a certain keyword
    @Query(value = "SELECT * FROM cs4550_finalProjSchema.serviceitems WHERE description LIKE :keyword or title LIKE :keyword",
            nativeQuery = true)
    public List<ServiceItem> findServiceItemsByKeyword(@Param("keyword") String keyword);
}
