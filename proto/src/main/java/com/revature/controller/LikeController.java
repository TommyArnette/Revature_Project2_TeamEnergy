package com.revature.controller;

import com.revature.models.JsonResponse;
import com.revature.models.Likes;
import com.revature.models.User;
import com.revature.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController("likeController")
@RequestMapping(value="api")
@CrossOrigin(value = "http://localhost:4200/", allowCredentials = "true")
public class LikeController {
    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService){this.likeService =likeService;}

    @PostMapping("likes")
    public JsonResponse createNewLike(HttpSession session, @RequestBody Likes likes){
        JsonResponse jsonResponse;

        User user = (User) session.getAttribute("loggedInUser");

        if(user != null){
            likes.setUserIdL(user.getUserId());
            likes.setUserF(user.getUserFirstName());
            likes.setUserL(user.getUserLastName());

            Likes newLike = this.likeService.createLike(likes);


            if(newLike != null){
                jsonResponse = new JsonResponse(true, "Like created.", likes);
            }else{
                jsonResponse = new JsonResponse(false, "No like created.", null);
            }
        }
        else{
            return jsonResponse = new JsonResponse(false, "You need to be logged in.", null);
        }

        return  jsonResponse;
    }
}
