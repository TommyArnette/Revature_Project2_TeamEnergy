package com.revature;

import com.revature.models.User;
import com.revature.repository.UserDao;
import com.revature.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Tests associated with the UserService class.
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    UserService userService;

    @Mock
    UserDao userDao;

    @BeforeEach
    void setUp(){
        this.userService = new UserService(userDao);
    }

    /**
     * Test to compare expected list of user objects to the actual list of user objects returned
     */
    @Test
    void selectAllUsers() {
        //assign
        List<User> expectedResult = new ArrayList<>();
        expectedResult.add(new User(1, "testUser", "password", "tommy", "arnette", "tommy.arnette@gmail.com", "just a dude", "whoops.jpg"));
        Mockito.when(userDao.findAll()).thenReturn(expectedResult);

        //act
        List<User> actualResult = this.userService.selectAllUsers();

        //assert
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test to compare expected user object returned by specific userId to the actual user object returned.
     */
    @Test
    void selectUserByIdNotNull() {
        //assign
        Integer id = 1;
        User expectedResult = new User(1, "testUser", "password", "tommy", "arnette", "tommy.arnette@gmail.com", "just a dude", "whoops.jpg");
        Mockito.when(userDao.findById(id)).thenReturn(Optional.of(expectedResult));

        //act
        User actualResult = this.userService.selectUserById(id);

        //assert
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test created to compare the expected User object returned when an invalid userId is entered.
     */
    @Test
    void selectUserByIdNull(){
        //assign
        Integer id = 1;
        User expectedResult = null;
        Mockito.when(userDao.findById(id)).thenReturn(Optional.empty());

        //act
        User actualResult = this.userService.selectUserById(id);

        //assert
        assertEquals(expectedResult, actualResult);
    }
}

