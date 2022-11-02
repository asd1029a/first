package com.hp.first.entity;

import com.hp.first.dto.OrderDto;
import lombok.Getter;

import javax.persistence.*;

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

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItemList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderDelivery orderDelivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    public Order createOrder(OrderDto orderDto) {
        this.id = orderDto.getId();
        this.member = orderDto.getMember();
        this.orderItemList = orderDto.getOrderItemList();
        this.orderDelivery = OrderDelivery.READY;
        this.orderStatus = OrderStatus.ORDER;
    }

    public void cancelOrder(Order order) {

    }
}
