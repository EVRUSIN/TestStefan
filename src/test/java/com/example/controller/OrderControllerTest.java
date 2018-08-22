package com.example.controller;

import com.example.model.Client;
import com.example.model.Order;
import com.example.model.User;
import com.example.model.enums.Currency;
import com.example.model.enums.Gender;
import com.example.repository.ClientRepository;
import com.example.repository.OrderRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository orderRepositoryMock;

    @MockBean
    private ClientRepository clientRepositoryMock;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private User user;
    private Client client;
    private Order order;

    @Before
    public void init(){
        this.user = new User("login1", "pass");
        this.client = new Client(user, "firstName", "lastName", LocalDate.now(), Gender.MALE, "1234567890");
        this.order = new Order(client, BigDecimal.valueOf(100), Currency.EUR);
    }

    @Test
    public void findAllOrdersTest() throws Exception{

        Iterable<Order> iterable = Arrays.asList(order);
        when(this.orderRepositoryMock.findAll()).thenReturn(iterable);

        this.mockMvc
                .perform(get("/order/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));


    }

    @Test
    public void findAllClientOrders() {
    }

    @Test
    public void createOrder() {
    }

    @Test
    public void updateStatus() {
    }
}