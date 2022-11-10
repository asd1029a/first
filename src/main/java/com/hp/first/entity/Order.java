package com.hp.first.entity;

import com.hp.first.status.OrderDelivery;
import com.hp.first.status.OrderStatus;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
public class Order extends BasicEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderDelivery orderDelivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public void addOrderItem(OrderItem orderItem) {
        orderItemList.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setMember(Member member) {
        this.member = member;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderDate(LocalDateTime localDateTime) {
        this.orderDate = localDateTime;
    }
    public static Order createOrder(Member member,List<OrderItem> orderItemList) {
        Order order = new Order();
        order.setMember(member);
        for (OrderItem orderItem : orderItemList) {
            order.addOrderItem(orderItem);
            orderItem.setOrder(order);
        }
        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    public int totalPrice() {
        return orderItemList.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }
}
