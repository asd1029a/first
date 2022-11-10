package com.hp.first.dto;

import com.hp.first.entity.Member;
import com.hp.first.status.OrderDelivery;
import com.hp.first.entity.OrderItem;
import com.hp.first.status.OrderStatus;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDto {

    @NotNull(message = "상품 아이디는 필수입니다.")
    private Long itemId;

    @Min(value = 1, message = "최소 한개이상 선택해주세요.")
    @Max(value = 999, message = "최대 999개 이하만 가능합니다.")
    private int count;
}
