package com.hp.first.entity;

import com.hp.first.exception.OutOfStockException;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockCount;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    public void removeStock(int stockCount) {
        int reStk = this.stockCount - stockCount;
        if(reStk < 0) {
            throw new OutOfStockException("재고가 부족합니다. 현재 남은 재고 :"+this.stockCount+"입니다.");
        }
        this.stockCount = reStk;
    }

}
