package com.store.services;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.store.entities.Order;

public interface OrderService {

	Order create(JsonNode orderData);

	Order findById(Integer id);

	List<Order> findByUsername(String username);

}
