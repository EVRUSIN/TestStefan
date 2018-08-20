package com.example.repository;

import com.example.model.Client;
import com.example.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends CrudRepository<Order, Long> {
    Iterable<Order> findByOwner(Client owner);

}
