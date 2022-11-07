package com.hp.first.dto;

import com.hp.first.entity.Member;
import com.hp.first.status.OrderDelivery;
import com.hp.first.entity.OrderItem;
import com.hp.first.status.OrderStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDto {

    private Long id;

    private Member member;

    private List<OrderItem> orderItemList = new ArrayList<>();

    private OrderDelivery orderDelivery;

    private OrderStatus orderStatus;
}
