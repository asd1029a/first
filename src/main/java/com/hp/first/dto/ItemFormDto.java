package com.hp.first.dto;

import com.hp.first.status.ItemStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

@Data
public class ItemFormDto {

    private Long id;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "가격을 입력해주세요.")
    private int price;

    @NotBlank(message = "수량을 입력해주세요.")
    private int stockCount;

    @NotBlank(message = "정보를 입력해주세요.")
    private String itemDetail;

    private ItemStatus itemStatus;
}
