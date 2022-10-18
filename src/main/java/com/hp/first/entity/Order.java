package com.hp.first.entity;

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

    private OrderStatus orderStatus;
}
