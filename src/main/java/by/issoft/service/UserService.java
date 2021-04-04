package by.issoft.service;

import by.issoft.domain.User;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserValidator userValidator;

    public UserService(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    public String createUser(User user) {
        if (userValidator.isValid(user)) {
            logger.debug("That's fine. User is valid. There are some logic to create new user and store it in DB.");
            return user.getUserID().toString();
        } else {
            logger.debug("User is invalid, can not create new user. Check lastname or firstname.");
            return null;
        }
    }

    public boolean addBalanceToUser(User user, int balance) {
        if (balance>=0){
            user.setBalance(user.getBalance() + balance);
            return true;
        }else {
            throw new UnsupportedOperationException("Not implement yet");
        }
    }


    /*TODO
    implement method addValidOrderID(Order order) that add orderID to User
    List<UUID> orderIDs
     */
/*
    public void removeOrderByID(User user, UUID orderId){
        OrderService orderService = new OrderService(new OrderStorage(), new OrderItemValidator());
        List<Order> userOrders = orderService.loadAllByUserId(user);
        logger.debug("Loaded " + userOrders.size() + " orders belongs to user " + user.getUserID());
        for (Order order: userOrders) {
            if (order.getOrderId().equals(orderId)){
                userOrders.remove(order);
                logger.debug("Successfully removed order " + orderId);
                user.setBalance(orderService.);
            }else {
                logger.debug("Order not found");
            }
        }
    }

*/
}
