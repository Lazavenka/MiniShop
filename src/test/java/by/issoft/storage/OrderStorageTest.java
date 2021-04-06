package by.issoft.storage;

import by.issoft.domain.Order;
import by.issoft.domain.User;
import by.issoft.domain.data.TestOrderSamples;
import by.issoft.domain.data.TestUserSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;


import static by.issoft.domain.data.TestOrderSamples.geterateOrder;
import static by.issoft.domain.data.TestUserSamples.generateUserWithID;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderStorageTest {
    private OrderStorage orderStorage;
    private Order order;

    @BeforeEach
    public void before() {
        orderStorage = new OrderStorage();
    }

    @Test
    public void saveOrderTest() {
        //given
        User user = generateUserWithID(UUID.fromString("cfa12c0-9384-499b-ba5c-52c4bb5f1471"));
        order = geterateOrder(user, 2);
        final String generatedID = order.getOrderId().toString();
        //when
        final String id = orderStorage.saveOrder(order);

        //then
        assertThat(id, is(not(nullValue())));
        final Order loadedOrder = orderStorage.loadOrder(id);
        assertEquals(loadedOrder, order);

    }

}