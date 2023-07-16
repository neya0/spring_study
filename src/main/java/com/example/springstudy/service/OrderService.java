package com.example.springstudy.service;

import com.example.springstudy.entity.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice, int count);
}
