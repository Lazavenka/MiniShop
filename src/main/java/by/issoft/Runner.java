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

public class Runner {
    public static void main(String[] args) {
        OrderStorage orderStorage = new OrderStorage();
        OrderItemValidator orderItemValidator = new OrderItemValidator();
        OrderService orderService = new OrderService(orderStorage, orderItemValidator);
        UserValidator userValidator = new UserValidator();
        UserService userService= new UserService(userValidator);

        User user1 = TestUserSamples.generateUser();
        System.out.println(user1);
        userService.addBalanceToUser(user1, 100000);
        System.out.println(user1);

        Order order1 = TestOrderSamples.geterateOrder(user1);
        Order order2 = TestOrderSamples.geterateOrder(user1);
        System.out.println(order1);
        System.out.println(order2);



    }
}
