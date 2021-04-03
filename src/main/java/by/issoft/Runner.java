package by.issoft;

import by.issoft.domain.Order;
import by.issoft.domain.OrderItem;
import by.issoft.domain.User;
import by.issoft.service.OrderItemValidator;
import by.issoft.service.OrderService;
import by.issoft.service.UserService;
import by.issoft.service.UserValidator;
import by.issoft.storage.OrderStorage;
import by.issoft.testData.TestOrderItemSamples;
import by.issoft.testData.TestOrderSamples;
import by.issoft.testData.TestUserSamples;

import java.util.List;
import java.util.UUID;

public class Runner {
    public static void main(String[] args) {
        OrderStorage orderStorage = new OrderStorage();
        OrderItemValidator orderItemValidator = new OrderItemValidator();
        OrderService orderService = new OrderService(orderStorage, orderItemValidator);
        UserValidator userValidator = new UserValidator();
        UserService userService= new UserService(userValidator);

        User user1 = TestUserSamples.generateUser();
        User userConstantID = TestUserSamples.generateUserWithID(UUID.fromString("0cad2eb4-56a9-484e-b7c1-4583e6eabf24"));
        System.out.println(user1);
        System.out.println(userConstantID);
        userService.addBalanceToUser(user1, 100000);
        userService.addBalanceToUser(userConstantID, 100000);
        System.out.println(user1);
        System.out.println(userConstantID);

        Order order1 = TestOrderSamples.geterateOrder(userConstantID);
        Order order2 = TestOrderSamples.geterateOrder(userConstantID);
        Order order3 = TestOrderSamples.geterateOrder(userConstantID);
        Order order4 = TestOrderSamples.geterateOrder(userConstantID);

        System.out.println(order1);
        System.out.println(order2);
        System.out.println(order3);
        System.out.println(order4);

        List<Order> orders = orderService.loadAllByUserId(userConstantID);

        order1 = orders.get(0);
        order2 = orders.get(1);
        order3 = orders.get(2);
        order4 = orders.get(3);

        System.out.println(order1);
        System.out.println(order2);
        System.out.println(order3);
        System.out.println(order4);


    }
}
