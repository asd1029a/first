package com.hp.first.dto;

import com.hp.first.entity.Member;
import com.hp.first.entity.OrderDelivery;
import com.hp.first.entity.OrderItem;
import com.hp.first.entity.OrderStatus;
import lombok.Data;

import javax.persistence.*;
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
