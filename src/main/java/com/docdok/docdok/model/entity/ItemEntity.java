package com.docdok.docdok.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private long id;
    private String name;
    private String description;
    private double cost;
}
