package com.energy;

import com.energy.models.User;
import com.energy.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/webapp/WEB-INF/applicationContext.xml");
    private static UserService userService;

    public static void main(String[] args) {
        userService = applicationContext.getBean("userService", UserService.class);
        /*User user1 = new User("testUser", "password", "john", "smith", "johnsmith@gmail.com",
                "test user", null);

        userService.registerNewUser(user1);*/

        System.out.println(userService.selectAllUsers());
    }
}
