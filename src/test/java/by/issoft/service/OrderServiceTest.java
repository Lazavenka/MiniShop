package by.issoft.service;

import by.issoft.domain.Order;
import by.issoft.domain.OrderItem;
import by.issoft.domain.OrderStatus;
import by.issoft.domain.User;
import by.issoft.domain.data.TestOrderItemSamples;
import by.issoft.domain.data.TestOrderSamples;
import by.issoft.domain.data.TestUserSamples;
import by.issoft.storage.OrderItemStorage;
import by.issoft.storage.OrderStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static by.issoft.domain.data.TestOrderItemSamples.validOrderItem;
import static by.issoft.domain.data.TestOrderSamples.getValidOrder;
import static by.issoft.domain.data.TestUserSamples.validUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;

class OrderServiceTest {
    private OrderService orderService;
    private Order order;

    @Mock
    private OrderStorage orderStorage;
    @Mock
    private OrderItemService orderItemService;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);

        orderService = new OrderService(orderStorage, orderItemService);
    }

    @Test
    void addValidOrderItem() {
        //given
        OrderItem item = validOrderItem();
        order = getValidOrder(validUser());

        when(orderItemService.createOrderItem(item)).thenReturn(any());

        assertTrue(orderService.addOrderItem(order, item)); //????
    }

    @Test
    void removeOrderItem() {
    }

    @Test
    void placeOrder() {
        //given
        User user = validUser();
        order = getValidOrder(user);

        final String orderID = order.getOrderId().toString();

        //expect
        assertThat(order.getOrderStatus(), is(OrderStatus.PENDING));

        when(orderService.placeOrder(order, user)).thenReturn(orderID);

        assertThat(order.getOrderStatus(), is(OrderStatus.ACCEPT));
    }

    @Test
    void placeOrder_invalidBalance() {
        //given
        OrderItem item = validOrderItem();
        User user = validUser();
        order = getValidOrder(user);
        user.setBalance(0);
        final String orderID = order.getOrderId().toString();

        //expect
        orderService.placeOrder(order, user);
        assertThat(order.getOrderStatus(), is(OrderStatus.DECLINED));
    }

    @Test
    void placeOrder_notBelongsToUser() {
        //given
        User validUser = validUser();
        order = getValidOrder(validUser);
        User otherUser = TestUserSamples.generateUser();
        validUser.setBalance(0);
        otherUser.setBalance(1000);
        final String orderID = order.getOrderId().toString();

        //expect
        orderService.placeOrder(order, otherUser);
        assertThat(order.getOrderStatus(), is(OrderStatus.DECLINED));
    }

    @Test
    void sendOrderToUser() {
        User validUser = validUser();
        order = getValidOrder(validUser);

        orderService.sendOrderToUser(order, validUser);
        assertThat(order.getOrderStatus(), is(OrderStatus.PENDING));

        orderService.placeOrder(order, validUser);
        orderService.sendOrderToUser(order, validUser);
        assertThat(order.getOrderStatus(), is(OrderStatus.COMPLETED));
    }
}