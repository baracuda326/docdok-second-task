package com.docdok.docdok.business.impl;

import com.docdok.docdok.business.UserService;
import com.docdok.docdok.exception.BadRequestException;
import com.docdok.docdok.exception.NotFoundException;
import com.docdok.docdok.model.Status;
import com.docdok.docdok.model.dto.Item;
import com.docdok.docdok.model.dto.Order;
import com.docdok.docdok.model.dto.User;
import com.docdok.docdok.model.dto.UserOrder;
import com.docdok.docdok.model.entity.ItemEntity;
import com.docdok.docdok.model.entity.OrderEntity;
import com.docdok.docdok.model.entity.UserEntity;
import com.docdok.docdok.repository.ItemRepository;
import com.docdok.docdok.repository.OrderRepository;
import com.docdok.docdok.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import static com.docdok.docdok.utils.Constants.FIRST_NAME;
import static com.docdok.docdok.utils.Constants.INPUT_IS_NOT_VALID;
import static com.docdok.docdok.utils.Mapper.*;
import static java.text.MessageFormat.format;
import static java.util.Objects.isNull;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ItemRepository itemRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public User getUserById(Long userId) {
        checkInput(userId);
        log.info("Get user by ID: {}", userId);
        return userEntityToUser(userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(format("User with id: {0} not found", userId))));
    }

    @Override
    public List<Order> getOrderByUserId(Long userId) {
        checkInput(userId);
        log.info("Get order by user ID: {}", userId);
        List<OrderEntity> orderEntities = orderRepository.findAll();
        return orderEntityToOrder(orderEntities.stream().filter(value -> value.getUser().getId() == userId)
                .collect(Collectors.toList()));
    }

    @Override
    public List<Item> getItemsByOrderId(Long orderId) {
        checkInput(orderId);
        log.info("Get items by order ID: {}", orderId);
        List<ItemEntity> itemEntities = new ArrayList<>();
        List<OrderEntity> orderEntities = orderRepository.findAll().stream()
                .filter(orderEntity -> orderEntity.getId() == orderId).collect(Collectors.toList());
        orderEntities.forEach(orderEntity -> itemEntities.addAll(orderEntity.getItemEntity()));
        return itemEntityListToItemList(itemEntities);
    }


    @Override
    public List<UserOrder> getUserOrders() {
        log.info("Get orders");
        List<OrderEntity> orders = orderRepository.findAllByDate(Calendar.getInstance().getTime(), Sort.by(FIRST_NAME));
        List<UserOrder> orderList = new ArrayList<>();
        orders.forEach(orderEntity -> orderList.add(createUserOrder(orderEntity)));
        log.info(format("Found orders: {0}", orders));
        return orderList;
    }

    private void checkInput(Object input) {
        if (isNull(input)) {
            log.warn(INPUT_IS_NOT_VALID);
            throw new BadRequestException(INPUT_IS_NOT_VALID);
        }
    }

    @PostConstruct
    public void init() {
        ItemEntity item1 = ItemEntity.builder().name("Nokia").description("Nokia text").cost(1500.10).build();
        ItemEntity item2 = ItemEntity.builder().name("Samsung").description("Samsung text").cost(3900.80).build();
        ItemEntity item3 = ItemEntity.builder().name("iPhone").description("iPhone text").cost(5000.00).build();
        ItemEntity item4 = ItemEntity.builder().name("iPad").description("iPad text").cost(7000.00).build();
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);

        UserEntity user1 = userRepository.save(createUser("Antony", "Kravich"));
        UserEntity user2 = userRepository.save(createUser("Danny", "Traho"));
        UserEntity user3 = userRepository.save(createUser("Genny", "Perec"));

        OrderEntity order1 = OrderEntity.builder().date(
                        new GregorianCalendar(2021, Calendar.FEBRUARY, 2).getTime())
                .user(user1).status(Status.CLOSED.name())
                .itemEntity(new ArrayList<>(List.of(item1, item3))).build();
        OrderEntity order2 = OrderEntity.builder().date(
                        new GregorianCalendar(2021, Calendar.AUGUST, 10).getTime())
                .user(user2).status(Status.CLOSED.name())
                .itemEntity(new ArrayList<>(List.of(item2))).build();
        OrderEntity order3 = OrderEntity.builder().date(
                        new GregorianCalendar(2021, Calendar.SEPTEMBER, 20).getTime())
                .user(user1).status(Status.CLOSED.name())
                .itemEntity(new ArrayList<>(List.of(item3))).build();
        OrderEntity order4 = OrderEntity.builder().date(
                        new GregorianCalendar(2021, Calendar.DECEMBER, 31).getTime())
                .user(user3).status(Status.OPENED.name())
                .itemEntity(new ArrayList<>(List.of(item1, item2, item3, item4))).build();
        OrderEntity order5 = OrderEntity.builder().date(
                        new GregorianCalendar(2021, Calendar.DECEMBER, 25).getTime())
                .user(user1).status(Status.OPENED.name())
                .itemEntity(new ArrayList<>(List.of(item4))).build();
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderRepository.save(order4);
        orderRepository.save(order5);
    }
}
