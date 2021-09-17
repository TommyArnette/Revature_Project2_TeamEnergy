package com.revature.controller;

import com.revature.models.Comments;
import com.revature.models.JsonResponse;
import com.revature.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for endpoints that handle the creation of comments.
 * Users can create comments on posts.
 */
@RestController("commentsController")
@RequestMapping(value="api")
@CrossOrigin(value = "http://localhost:4200/", allowCredentials = "true")
public class CommentsController {
    private CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService){
        this.commentsService = commentsService;
    }

    /**
     * Creates a comment on a post.
     *
     * @param comments  the comment the user created and assigned to a post
     * @return          returns JsonResponse
     */
    @PostMapping("comments")
    public JsonResponse createNewComment(@RequestBody Comments comments){
        JsonResponse jsonResponse;

        Comments newComment = this.commentsService.createComment(comments);

        if(newComment != null){
            jsonResponse = new JsonResponse(true, "Comment added to post.", comments);
        }else{
            jsonResponse = new JsonResponse(false, "No comment added.", null);
        }
        return jsonResponse;
    }
}
