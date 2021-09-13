package com.energy.controller;


import com.energy.models.JsonResponse;
import com.energy.models.User;
import com.energy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@RestController("userController")
@RequestMapping(value="api")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService){this.userService = userService;}

    @GetMapping("user")
    public JsonResponse selectAllUsers(){
        return new JsonResponse(true,"Obtained all users",this.userService.selectAllUsers());
    }

    @PostMapping("user")
    public JsonResponse registerNewUser(@RequestBody User user) throws MessagingException {
        JsonResponse jsonResponse;
        User tempU = this.userService.registerNewUser(user);
        if(tempU==null){
            jsonResponse =new JsonResponse(false,"username already exist", null);
        }else{
            jsonResponse= new JsonResponse(true,"user created",user);
        }
        return jsonResponse;
    }

    //@PatchMapping("user/UD")
    // public JsonResponse updateUserInfo(@RequestBody User user){

    // JsonResponse jsonResponse;
    //User tempU = this.userService.updateUserInfo(user);
    // return new JsonResponse(true,"user updated",tempU);
    // }


    @GetMapping("user/{userId}")
    public JsonResponse selectUserById(@PathVariable Integer userId){
        JsonResponse jsonResponse;
        User user = this.userService.selectUserById(userId);
        if(user != null){
            jsonResponse = new JsonResponse(true,"user found",user);
        }else{
            jsonResponse = new JsonResponse(false,"user not found",null);
        }
        return jsonResponse;
    }
    @GetMapping("user/UN/{username}")
    public JsonResponse selectUserByUsername(@PathVariable String username){
        JsonResponse jsonResponse;
        User user = this.userService.selectUserByUsername(username);
        if(user != null){
            jsonResponse = new JsonResponse(true,"user found",user);
        }else{
            jsonResponse = new JsonResponse(false,"user not found",null);
        }
        return jsonResponse;
    }

    @PostMapping("login")
    public JsonResponse login(HttpSession session, @RequestBody User users) {
        JsonResponse jsonResponse;

        User user = this.userService.login(users);

        if (user == null) {
            jsonResponse = new JsonResponse(false,"data incorrect",null);
        }else{
            jsonResponse= new JsonResponse(true, "loggin and session created",user);
            session.setAttribute("loggedInUser",user);
        }

        return jsonResponse;
    }

    @GetMapping("logout")
    public JsonResponse logout(HttpSession session){
        session.setAttribute("loggedInUser",null);
        return new JsonResponse(true,"session terminated",null);
    }

    @GetMapping("check-session")
    public JsonResponse checkSession(HttpSession session){
        JsonResponse jsonResponse;
        User user = (User) session.getAttribute("loggedInUser");
        if(user !=null){
            jsonResponse = new JsonResponse(true,"session found",user);
        }else{
            jsonResponse = new JsonResponse(false, "no session found",null);
        }
        return jsonResponse;
    }

}