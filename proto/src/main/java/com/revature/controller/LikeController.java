package com.revature.controller;

import com.revature.models.JsonResponse;
import com.revature.models.Likes;
import com.revature.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Controller for Like HTTP methods and endpoints.
 * @RestController is used to imply JSON request body language
 * @CrossOrigin is used to solve browser CORS error messaging
 * Initial endpoint "api" defined by the @RequestMapping annotation
 */
@RestController("likeController")
@RequestMapping(value="api")
@CrossOrigin(value = "http://localhost:4200/", allowCredentials = "true")
public class LikeController {
    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService){this.likeService =likeService;}

    /**
     * POST method used to create a new Like object and associated it with a specific Post object.
     *
     * @param likes     passes a Like object to the method
     * @return          returns a JsonResponse method (containing success, message, data)
     */
    @PostMapping("likes")
    public JsonResponse createNewLike(@RequestBody Likes likes){
        JsonResponse jsonResponse;

        Likes newLike = this.likeService.createLike(likes);
        
        if(newLike != null){
            jsonResponse = new JsonResponse(true, "Like created.", likes);
        }else{
            jsonResponse = new JsonResponse(false, "No like created.", null);
        }
        return jsonResponse;
    }

    /**
     * Method to delete a like that has been assigned to a Post object. In the project, when a user clicks on a Post that
     * they had liked, this method will be called and the like will be deleted.
     *
     * @param likeId    the ID of the like object to be removed
     * @return          returns a JsonResponse
     */
    @DeleteMapping("likes/{likeId}")
    public JsonResponse deleteLike(@PathVariable Integer likeId){
        this.likeService.deleteLike(likeId);
        return new JsonResponse(true, "Like removed.", null);
    }
}
