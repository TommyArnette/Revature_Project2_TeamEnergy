package com.energy.controller;


import com.energy.models.JsonResponse;
import com.energy.models.User;
import com.energy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@RestController("userController")
@CrossOrigin(value = "http://localhost:9000", allowCredentials = "true")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("user")
    public JsonResponse getAllUsers(){
        System.out.println("Testing");
        return new JsonResponse(true, "Obtained all users", this.userService.selectAllUsers());
    }

    @PostMapping("user")
    public JsonResponse createUser(@RequestBody User user) throws MessagingException {
        this.userService.registerNewUser(user);

        return new JsonResponse(true, "User has been registered.", user);
    }

    @PostMapping("update")
    public JsonResponse updateUser(@RequestBody User user) throws MessagingException{
        this.userService.updateUserInfo(user);
        return new JsonResponse(true, "User profile has been updated.", null);
    }

    @GetMapping("user/{userId}")
    public JsonResponse getUserById(@PathVariable Integer userId){
        JsonResponse jsonResponse;

        User user = this.userService.selectUserById(userId);

        if (user != null) {
            jsonResponse = new JsonResponse(true, "User with user ID: " + userId + " has been found.", user);
        }
        else{
            jsonResponse = new JsonResponse(false, "User could not be found.", null);
        }
        return jsonResponse;
    }

    @GetMapping("check-session")
    public JsonResponse checkSession(HttpSession httpSession){
        JsonResponse jsonResponse;

        User user = (User) httpSession.getAttribute("loggedInUser");

        if(user != null){
            jsonResponse = new JsonResponse(true, "Session found.", user);
        }
        else{
            jsonResponse = new JsonResponse(false, "No session found.", null);
        }
        return jsonResponse;
    }

    @GetMapping("login")
    public JsonResponse login(HttpSession httpSession, @RequestBody User user){
        httpSession.setAttribute("loggedInUser", user);

        return new JsonResponse(true, "User logged in and session created.", user);
    }

    @GetMapping("logout")
    public JsonResponse logout(HttpSession httpSession){
        httpSession.setAttribute("loggedInUser", null);

        return new JsonResponse(true, "Session terminated.", null);
    }
}
