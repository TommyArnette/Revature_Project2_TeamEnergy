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
        return new JsonResponse(true,"Displaying all users",this.userService.selectAll());
    }

    @PostMapping("user")
    public JsonResponse createUser(@RequestBody User user) throws MessagingException {
        this.userService.registerNewUser(user);
        return new JsonResponse(true,"user has been created",null);
    }

    @PostMapping("update")
    public JsonResponse updateUser(@RequestBody User user) throws MessagingException {
        this.userService.updateUserInfo(user);
        return new JsonResponse(true,"user has been update",null);
    }

    @GetMapping("user/{id}")
    public JsonResponse getById(@PathVariable Integer id){

        JsonResponse jsonResponse;
        User user = this.userService.selectUserById(id);
        if(user != null){
            jsonResponse = new JsonResponse(true,"user found",user);
        }else{
            jsonResponse = new JsonResponse(false,"user with id " + id + " does not exist",null);
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




