package com.docdok.docdok.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOrder {
    private Long userId;
    private Long orderId;
    private String userFullName;
    private String orderDate;
    private List<Item> items;
}
