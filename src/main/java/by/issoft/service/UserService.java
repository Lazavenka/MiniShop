package by.issoft.service;

import by.issoft.domain.Order;
import by.issoft.domain.User;
import by.issoft.storage.OrderStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserValidator userValidator;
    private final OrderStorage orderStorage;

    public UserService(UserValidator userValidator, OrderStorage orderStorage) {
        this.userValidator = userValidator;
        this.orderStorage = orderStorage;
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

    public boolean changeUserBalance(User user, int balance) {
        final int newBalance = user.getBalance() + balance;
        if (newBalance>=0){
            user.setBalance(user.getBalance() + balance);
            return true;
        }else {
            logger.debug("Incorrect operation. User balance can't be negative.");
            return false;
        }
    }

    public boolean addOrder(User user, Order order) {
        user.getOrderIDs().add(order.getOrderId());
        final String orderID = orderStorage.saveOrder(order);
        //updateUser in DB
        logger.debug("Order #" + orderID + " successfully added to user #" + user.getUserID());
        return true;
    }

}
