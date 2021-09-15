package com.revature.service;

import com.revature.models.User;
import com.revature.repository.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Test
    void selectAllUsers() {
        //assign
        List<User> expectedResult = new ArrayList<>();
        expectedResult.add(new User(1,"test","pass123","preston","bonomo","pcbonomo@gmail.com","profile about me","profile pic"));
        Mockito.when(userDao.findAll()).thenReturn(expectedResult);

        //act
        List<User> actualResult = this.userService.selectAllUsers();

        //assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void selectUserByIdNotNull() {
        //assign
        Integer id = 1;
        User expectedResult = new User(1,"testUser","pass123","preston","bonomo","pcbonomo@gmail.com","profile about me","profile pic");
        Mockito.when(userDao.findById(id)).thenReturn(Optional.of(expectedResult));

        //act
        User actualResult = this.userService.selectUserById(id);

        //assert
        assertEquals(expectedResult, actualResult);
    }

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

