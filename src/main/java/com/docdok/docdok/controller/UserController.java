package com.docdok.docdok.controller;

import com.docdok.docdok.model.dto.Item;
import com.docdok.docdok.model.dto.Order;
import com.docdok.docdok.model.dto.User;
import com.docdok.docdok.model.dto.UserOrder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(path = "api/")
public interface UserController {

    @ApiOperation(value = "Get user by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @GetMapping(value = "user/{user_id}")
    ResponseEntity<User> getUserById(@PathVariable(value = "user_id") Long userId);

    @ApiOperation(value = "Get orders by user Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Order[].class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @GetMapping(value = "order/{user_id}")
    ResponseEntity<List<Order>> getOrderByUserId(@RequestParam(value = "user_id") Long userId);

    @ApiOperation(value = "Get items by order Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Item[].class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @GetMapping(value = "items/{order_id}")
    ResponseEntity<List<Item>> getItemsByOrderId(@RequestParam(value = "order_id") Long orderId);

    @ApiOperation(value = "Get orders")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserOrder[].class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @GetMapping(value = "orders/")
    ResponseEntity<List<UserOrder>> getUserOrders();

}
