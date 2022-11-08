package com.hp.first.dto;

import com.hp.first.status.ItemStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchDto {

    private String searchDateType;
    private ItemStatus itemStatus;
    private String searchBy;
    private String searchQuery = "";
}
