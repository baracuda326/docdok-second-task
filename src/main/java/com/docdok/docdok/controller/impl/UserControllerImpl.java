package com.docdok.docdok.controller.impl;

import com.docdok.docdok.business.UserService;
import com.docdok.docdok.controller.UserController;
import com.docdok.docdok.model.dto.Item;
import com.docdok.docdok.model.dto.Order;
import com.docdok.docdok.model.dto.User;
import com.docdok.docdok.model.dto.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     * @return ResponseEntity<User>
     */
    @Override
    public ResponseEntity<User> getUserById(Long userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    /**
     * @return ResponseEntity<List < Order>>
     */
    @Override
    public ResponseEntity<List<Order>> getOrderByUserId(Long userId) {
        return new ResponseEntity<>(userService.getOrderByUserId(userId), HttpStatus.OK);
    }

    /**
     * @return ResponseEntity<List < Item>>
     */
    @Override
    public ResponseEntity<List<Item>> getItemsByOrderId(Long userId) {
        return new ResponseEntity<>(userService.getItemsByOrderId(userId), HttpStatus.OK);
    }

    /**
     * @return ResponseEntity<List < UserOrder>>
     */
    @Override
    public ResponseEntity<List<UserOrder>> getUserOrders() {
        return new ResponseEntity<>(userService.getUserOrders(), HttpStatus.OK);
    }
}
