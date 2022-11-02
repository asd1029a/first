package com.hp.first.entity;

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

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

}
