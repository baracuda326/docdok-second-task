package com.docdok.docdok.business;

import com.docdok.docdok.model.dto.Item;
import com.docdok.docdok.model.dto.Order;
import com.docdok.docdok.model.dto.User;
import com.docdok.docdok.model.dto.UserOrder;

import java.util.List;

public interface UserService {
    User getUserById(Long userId);

    List<Order> getOrderByUserId(Long userId);

    List<Item> getItemsByOrderId(Long orderId);

    List<UserOrder> getUserOrders();
}
