package com.energy;

import com.energy.models.User;
import com.energy.service.UserService;
import com.energy.service.UserServiceImpl;

public class Main {
    public static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        User u1 = new User("testUserName", "password", "john", "smith", "johnsmith@gmail.com");

        userService.registerNewUser(u1);

    }
}
