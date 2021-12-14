package com.docdok.docdok.repository;

import com.docdok.docdok.model.entity.OrderEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query("select a from OrderEntity a where a.date >= :currentDate")
    List<OrderEntity> findAllByDate(@Param("currentDate") Date currentDate, Sort firstName);
}
