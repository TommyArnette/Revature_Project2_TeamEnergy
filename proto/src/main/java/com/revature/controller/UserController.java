package com.revature.controller;

import com.revature.models.JsonResponse;
import com.revature.models.User;
import com.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

/**
 * Used to control User object methods and endpoints
 */
@RestController("userController")
@RequestMapping(value="api")
@CrossOrigin(value = "http://localhost:4200/", allowCredentials = "true")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){this.userService = userService;}

    /**
     * Used to get all User objects.
     *
     * @return  returns JsonResponse that provides a List of User objects registered with Team Energy Social Network
     */
    @GetMapping("user")
    public JsonResponse selectAllUsers(){
        return new JsonResponse(true,"Obtained all users.",this.userService.selectAllUsers());
    }

    /**
     * Creates a new User object that is registered with Team Energy Social Network. Upon registration, User object is logged into
     * the network and a session is created.
     *
     * @param session   session information passed to the method
     * @param user      new User object passed to the method
     * @return          returns JsonResponse containing User object info if registration successful
     * @throws MessagingException throws exception if invalid email
     */
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

    /**
     * Used to update User profile information.
     * User information can be updated all at once (first name, email, in one transaction) or singular pieces of information
     * in one transaction.
     *
     * @param user  User object whose information will be updated
     * @return      returns the same User object with updated information
     */
    @PatchMapping("user/update")
    public JsonResponse updateUser(@RequestBody User user){
        User u = this.userService.updateUser(user);

        return new JsonResponse(true, "User information updated.", user);
    }

    /**
     * Selects a User specific the that User objects userId.
     *
     * @param userId    userId passed to method to find a User object
     * @return          User object returned
     */
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

    /**
     * Used to select a User object by their username.
     *
     * @param username  username passed to method to find a specific user
     * @return          returns the User object found by the username
     */
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

    /**
     * Used to log User object into the Team Energy Social Network app.
     *
     * @param session   session info obtained from User object after signing in
     * @param user      User information obtained from user input information
     * @return          returns JsonResponse indicating login success or failure
     */
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

    /**
     * Used to log a User object out of social network.
     *
     * @param session   session information associated with User object
     * @return          returns JsonResponse indicating successful logout
     */
    @PostMapping("logout")
    public JsonResponse logout(HttpSession session){
        session.setAttribute("loggedInUser",null);
        return new JsonResponse(true,"Session terminated.",null);
    }

    /**
     * Used to check the current session information associated with the User that is signed in.
     *
     * @param session   session information associated with signed in User object
     * @return          returns JsonResponse providing User object information
     */
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

    /**
     * Used to send an email and generate a link that is sent to the userEmail that indicates a password reset is necessary.
     *
     * @param user  User object information passed to the method
     * @return      returns JsonResponse with password reset link information
     * @throws MessagingException
     */
    @PostMapping("forgot")
    public JsonResponse generateLink(@RequestBody User user) throws MessagingException {
        JsonResponse jsonResponse;
        User Nuser = this.userService.selectUserByEmail(user.getUserEmail());
        if (Nuser == null) {
            jsonResponse = new JsonResponse(false,"Email doesnt exist",null);
        }else{
            User sta =this.userService.generateLink(Nuser);

            jsonResponse= new JsonResponse(true, "Link created and send",sta);
        }
        return jsonResponse;
    }

    /**
     * Used to create a new password for a User object
     *
     * @param user          User object that initiated the password reset
     * @param resetToken    password reset token that is used to verify the User object and reset password
     * @return              returns JsonResponse indicating password reset success and updated user information
     */
    @PostMapping("user/token/{resetToken}")
    public JsonResponse createNewPass(@RequestBody User user, @PathVariable String resetToken){
        JsonResponse jsonResponse;
        User Nuser = this.userService.selectByToken(resetToken);

        if (Nuser == null) {
            jsonResponse = new JsonResponse(false,"token invalid",null);
        }else{
            Nuser.setPassword(user.getPassword());

            jsonResponse= new JsonResponse(true, "Link created and send",this.userService.saveUserWithNewPassword(Nuser));
        }
        return jsonResponse;
    }
}
