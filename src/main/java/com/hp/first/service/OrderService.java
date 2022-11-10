package com.hp.first.service;

import com.hp.first.dto.OrderDto;
import com.hp.first.dto.OrderHistDto;
import com.hp.first.dto.OrderItemDto;
import com.hp.first.entity.*;
import com.hp.first.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    private MemberRepository memberRepository;

    private ItemRepository itemRepository;

    private ItemImgRepository itemImgRepository;

    public Long order(OrderDto orderDto, String email) {
        Item item = itemRepository.findById(orderDto.getItemId()).orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);

        return orderRepository.save(Order.createOrder(member, orderItemList)).getId();

    }

    public Page<OrderHistDto> getOrderList(String email, Pageable pageable) {
        List<Order> orders = orderRepository.findOrders(email, pageable);
        Long totalCount = orderRepository.countOrder(email);

        List<OrderHistDto> orderHistDtoList = new ArrayList<>();

        for (Order order : orders) {
            OrderHistDto orderHistDto = new OrderHistDto(order);
            List<OrderItem> orderItemList = order.getOrderItemList();
            for (OrderItem orderItem : orderItemList) {
                ItemImg itemImg = itemImgRepository.findByItemIdAndRepImgYn(orderItem.getItem().getId(), "Y");
                OrderItemDto orderItemDto = new OrderItemDto(orderItem, itemImg.getUrl());
                orderHistDto.addOrderItemDto(orderItemDto);
            }
            orderHistDtoList.add(orderHistDto);
        }
        return new PageImpl<OrderHistDto>(orderHistDtoList, pageable, totalCount);
    }

}
