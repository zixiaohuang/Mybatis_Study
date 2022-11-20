package com.hero.mapper;

import com.hero.pojo.Order;

import java.util.List;

public interface OrderMapper {

    /**
     * 查询所有订单
     * 返回值：订单list集合
     */
    List<Order> findAll();
}
