package by.issoft.service;

import by.issoft.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static by.issoft.domain.data.TestUserSamples.validUser;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private User user;
    private UserService userService;
    private UserValidator userValidator;

    @BeforeEach
    public void before(){
        userValidator = mock(UserValidator.class);
        userService = new UserService(userValidator);
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
    void addBalanceToUser() {
        //given
        user = validUser();
        final int balance = 1000;
        user.setBalance(balance);
        final int salary = 500;

        userService.addBalanceToUser(user, salary);
        //expect
        assertEquals(balance+salary, user.getBalance());

        //then


    }
    @Test
    void addNegativeBalanceToUser() {
        //given
        user = validUser();
        final int balance = 1000;
        user.setBalance(balance);

        final int salary = -1500;

        //expect
        assertThrows(UnsupportedOperationException.class, ()->userService.addBalanceToUser(user,salary));
    }

}