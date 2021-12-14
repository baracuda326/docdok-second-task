package com.docdok.docdok.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private long id;
    private Date date;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = ItemEntity.class)
    @JoinColumn(name = "item_entity_id")
    private List<ItemEntity> itemEntity;
}
