package com.revature.controller;

import com.revature.models.JsonResponse;
import com.revature.models.User;
import com.revature.service.UserService;
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
        return new JsonResponse(true,"Obtained all users.",this.userService.selectAllUsers());
    }

    @PostMapping("user")
    public JsonResponse registerNewUser(HttpSession session, @RequestBody User user) throws MessagingException {
        JsonResponse jsonResponse;
        User tempU = this.userService.registerNewUser(user);
        if(tempU==null){
            jsonResponse =new JsonResponse(false,"Cannot create, username already exists.", null);
        }else{
            session.setAttribute("loggedInUser",tempU);
            jsonResponse= new JsonResponse(true,"User registered, session created.",user);
        }
        return jsonResponse;
    }

    //@PatchMapping("user/UD")
   // public JsonResponse updateUserInfo(@RequestBody User user){

       // JsonResponse jsonResponse;
        //User tempU = this.userService.updateUserInfo(user);
       // return new JsonResponse(true,"user updated",tempU);
   // }
       @PatchMapping("user/update/{userId}")
       public JsonResponse updateFirstName(HttpSession session, @PathVariable Integer userId, @RequestBody String userFirstName){
           JsonResponse jsonResponse;
           User currentUser = (User) session.getAttribute("loggedInUser");

           currentUser = userService.selectUserById(userId);

           if(currentUser != null){
               //this.userService.updateUserFirstName(currentUser.getUserFirstName());
               currentUser.setUserFirstName(userFirstName);
               currentUser = this.userService.updateUserFirstName(currentUser);
               jsonResponse = new JsonResponse(true, "User information updated.", currentUser);
           }else{
               jsonResponse = new JsonResponse(false, "Must be logged in.", null);
           }

           return jsonResponse;
       }


    @GetMapping("user/{userId}")
    public JsonResponse selectUserById(@PathVariable Integer userId){
        JsonResponse jsonResponse;
        User user = this.userService.selectUserById(userId);
        if(user != null){
            jsonResponse = new JsonResponse(true,"Found user.",user);
        }else{
            jsonResponse = new JsonResponse(false,"No user found.",null);
        }
        return jsonResponse;
    }
    @GetMapping("user/username/{username}")
    public JsonResponse selectUserByUsername(@PathVariable String username){
        JsonResponse jsonResponse;
        User user = this.userService.selectUserByUsername(username);
        if(user != null){
            jsonResponse = new JsonResponse(true,"Found user.",user);
        }else{
            jsonResponse = new JsonResponse(false,"No user found.",null);
        }
        return jsonResponse;
    }

    @PostMapping("login")
    public JsonResponse login(HttpSession session, @RequestBody User user) {
        JsonResponse jsonResponse;

        User tUser = this.userService.login(user);

        if (tUser == null) {
            jsonResponse = new JsonResponse(false,"Incorrect credentials.",null);
        }else{
            jsonResponse= new JsonResponse(true, "Login and session created.",tUser);
            session.setAttribute("loggedInUser",tUser);
        }

        return jsonResponse;
    }

    @GetMapping("logout")
    public JsonResponse logout(HttpSession session){
        session.setAttribute("loggedInUser",null);
        return new JsonResponse(true,"Session terminated.",null);
    }

    @GetMapping("check-session")
    public JsonResponse checkSession(HttpSession session){
        JsonResponse jsonResponse;
        User user = (User) session.getAttribute("loggedInUser");
        if(user !=null){
            jsonResponse = new JsonResponse(true,"Session found.",user);
        }else{
            jsonResponse = new JsonResponse(false, "No session found.",null);
        }
        return jsonResponse;
    }

}
