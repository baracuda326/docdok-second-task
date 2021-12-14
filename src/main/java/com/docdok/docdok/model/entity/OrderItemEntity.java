package com.docdok.docdok.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "order_item")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntity implements Serializable {
    private static final long serialVersionUID = 5555943036168201609L;
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private long id;
    private long orderId;
    private long itemId;
}
