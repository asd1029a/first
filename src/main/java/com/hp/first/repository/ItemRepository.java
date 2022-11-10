package com.hp.first.repository;

import com.hp.first.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {

    List<Item> findAllByItemName(String itemName);
    List<Item> findAllByItemNameOrItemDetail(String itemName, String itemDetail);

    List<Item> findAllByPriceLessThan(Integer price);

    List<Item> findALlByPriceLessThanOrderByPriceDesc(Integer price);

    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> finalAllByItemDetail(@Param("itemDetail") String itemDetail);

}
