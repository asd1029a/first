package com.hp.first.dto;

import com.hp.first.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItemDto {

    public OrderItemDto(OrderItem orderItem, String imgUrl) {
        this.name = orderItem.getItem().getName();
        this.count = orderItem.getCount();
        this.orderPrice = orderItem.getOrderPrice();
        this.imgUrl = imgUrl;
    }

    private String name;
    private int count;
    private String imgUrl;
    private int orderPrice;
}
