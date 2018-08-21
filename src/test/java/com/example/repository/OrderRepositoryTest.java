package com.example.repository;

import com.example.model.Client;
import com.example.model.Order;
import com.example.model.User;
import com.example.model.enums.Currency;
import com.example.model.enums.Gender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest

public class OrderRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    private User user;
    private Client client;
    private Order order;

    @Before
    public void init() {
        this.user = new User("login1", "pass");
        this.client = new Client(user, "firstName", "lastName", LocalDate.now(), Gender.MALE, "1234567890");
        this.order = new Order(client, BigDecimal.valueOf(100), Currency.EUR);
        entityManager.persist(user);
        entityManager.persist(client);
    }

    @Test
    public void findByOwner() {
        entityManager.persist(order);
        Iterable<Order> iterable = this.orderRepository.findByOwner(client);
        assertThat(iterable).isNotNull().hasSize(1).containsExactlyInAnyOrder(order);
    }

    @Test
    public void shouldReturnEmptyIterable() {
        Iterable<Order> iterable = this.orderRepository.findByOwner(client);
        assertThat(iterable).isNotNull().hasSize(0);
    }

}