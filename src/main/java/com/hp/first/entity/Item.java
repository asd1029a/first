package com.hp.first.entity;

import com.hp.first.dto.ItemFormDto;
import com.hp.first.exception.OutOfStockException;
import com.hp.first.status.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Item extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private int stockCount;

    @Lob
    @Column(nullable = false)
    private String itemDetail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemStatus itemStatus;

    public void updateItem(ItemFormDto itemFormDto) {
        this.name = itemFormDto.getName();
        this.price = itemFormDto.getPrice();
        this.stockCount = itemFormDto.getStockCount();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemStatus = itemFormDto.getItemStatus();
    }
    public void removeStock(int stockCount) {
        int reStk = this.stockCount - stockCount;
        if(reStk < 0) {
            throw new OutOfStockException("재고가 부족합니다. 현재 남은 재고 :"+this.stockCount+"입니다.");
        }
        this.stockCount = reStk;
    }

}
