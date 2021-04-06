package by.issoft.service;

import by.issoft.domain.OrderItem;
import by.issoft.storage.OrderItemStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static by.issoft.domain.data.TestOrderItemSamples.validOrderItem;
import static org.junit.jupiter.api.Assertions.*;

class OrderItemServiceTest {
    private OrderItemService orderItemService;
    private OrderItem orderItem;

    private OrderItemValidator orderItemValidator;
    private OrderItemStorage orderItemStorage;

    @BeforeEach
    public void before(){
        orderItemValidator = new OrderItemValidator();
        orderItemStorage = new OrderItemStorage();
        orderItemService = new OrderItemService(orderItemStorage, orderItemValidator);
    }
    @Test
    void createOrderItem_valid() {
        //given
        orderItem = validOrderItem();
        final String id = orderItem.getItemID().toString();

        assertEquals(id, orderItemService.createOrderItem(orderItem));
    }
    @Test
    void createOrderItem_invalid(){
        orderItem = validOrderItem();
        orderItem.setCount(-5);

        assertNull(orderItemService.createOrderItem(orderItem));
    }
}