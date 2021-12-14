package com.docdok.docdok.utils;

import com.docdok.docdok.model.dto.Item;
import com.docdok.docdok.model.dto.Order;
import com.docdok.docdok.model.dto.User;
import com.docdok.docdok.model.dto.UserOrder;
import com.docdok.docdok.model.entity.ItemEntity;
import com.docdok.docdok.model.entity.OrderEntity;
import com.docdok.docdok.model.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public final class Mapper {
    private Mapper() {
    }

    public static User userEntityToUser(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public static List<Item> itemEntityListToItemList(List<ItemEntity> itemList) {
        List<Item> list = new ArrayList<>();
        itemList.forEach(item -> list.add(itemEntityToItem(item)));
        return list;
    }

    public static Item itemEntityToItem(ItemEntity item) {
        return Item.builder().id(item.getId()).name(item.getName())
                .description(item.getDescription()).cost(item.getCost()).build();
    }

    public static List<Order> orderEntityToOrder(List<OrderEntity> orders) {
        List<Order> orderList = new ArrayList<>();
        for (OrderEntity order : orders) {
            orderList.add(Order.builder().id(order.getId())
                    .date(order.getDate()).status(order.getStatus()).build());
        }
        return orderList;
    }

    public static UserEntity createUser(String firstName, String lastName) {
        return UserEntity.builder().firstName(firstName)
                .lastName(lastName).build();
    }


    public static UserOrder createUserOrder(OrderEntity order) {
        return UserOrder.builder()
                .userId(order.getUser().getId())
                .orderId(order.getId())
                .userFullName(order.getUser().getFirstName() + " " + order.getUser().getLastName())
                .orderDate(order.getDate().toString())
                .items(itemEntityListToItemList(order.getItemEntity()))
                .build();
    }

}
