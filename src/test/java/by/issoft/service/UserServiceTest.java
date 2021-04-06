package by.issoft.service;

import by.issoft.domain.User;
import by.issoft.storage.OrderStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static by.issoft.domain.data.TestUserSamples.validUser;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private User user;
    private UserService userService;
    private UserValidator userValidator;
    private OrderStorage orderStorage;

    @BeforeEach
    public void before() {
        userValidator = mock(UserValidator.class);
        orderStorage = mock(OrderStorage.class);
        userService = new UserService(userValidator, orderStorage);
    }

    @Test
    void createUser() {
        //given valid user
        user = validUser();

        //when
        when(userValidator.isValid(user)).thenReturn(true);
        final String id = userService.createUser(user);

        //then
        assertEquals(id, user.getUserID().toString());
    }

    @Test
    void createUser_invalid() {
        //given invalid user
        user = validUser();
        user.setFirstName(null);

        when(userValidator.isValid(user)).thenReturn(false);
        final String id = userService.createUser(user);

        //expect
        assertNull(id);
    }

    @Test
    void setCorrectBalanceToUser() {
        //given
        user = validUser();
        final int balance = 1000;
        user.setBalance(balance);
        final int salary = 500;

        //expect
        assertTrue(userService.changeUserBalance(user, salary));
        assertEquals(balance + salary, user.getBalance());
    }

    @Test
    void setNegativeBalanceToUser() {
        //given
        user = validUser();
        final int balance = 1000;
        user.setBalance(balance);

        final int salary = -1500;

        //expect
        assertFalse(userService.changeUserBalance(user, salary));
    }

}