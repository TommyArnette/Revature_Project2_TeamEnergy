package com.revature.controller;


import com.revature.models.JsonResponse;
import com.revature.models.User;
import com.revature.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    UserController userController;

    @Mock
    UserService userService;

    @BeforeEach
    void setUp() {
        this.userController = new UserController(userService);

    }


    @Test
    void selectAllUsers() {
        //assign
        List<User> users = new ArrayList<>();
        users.add(new User(1, "test", "pass123", "preston", "bonomo", "pcbonomo@gmail.com", "profile about me", "profile pic"));
        JsonResponse expectedResult = new JsonResponse(true, "Obtained all users.", users);

        //mock user service
        Mockito.when(userService.selectAllUsers()).thenReturn(users);

        //act
        JsonResponse actualResult = this.userController.selectAllUsers();

        //assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void selectUserByIdNotNull() {
        //assign
        User users = new User(1, "test", "pass123", "preston", "bonomo", "pcbonomo@gmail.com", "profile about me", "profile pic");
        JsonResponse expectedResult = new JsonResponse(true, "Found user.", users);
        Mockito.when(userService.selectUserById(users.getUserId())).thenReturn(users);

        //act
        JsonResponse actualResult = this.userController.selectUserById(users.getUserId());

        //assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void selectUserByIdNull() {
        //assign
        User users = new User(1, "test", "pass123", "preston", "bonomo", "pcbonomo@gmail.com", "profile about me", "profile pic");
        JsonResponse expectedResult = new JsonResponse(false, "No user found.", null);
        Mockito.when(userService.selectUserById(users.getUserId())).thenReturn(null);

        //act
        JsonResponse actualResult = this.userController.selectUserById(users.getUserId());

        //assert
        assertEquals(expectedResult, actualResult);

    }

}