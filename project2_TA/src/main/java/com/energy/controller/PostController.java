package com.energy.controller;

import com.energy.models.JsonResponse;
import com.energy.models.Post;
import com.energy.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("postController")
@CrossOrigin(value = "http://localhost:9000", allowCredentials = "true")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("post")
    public JsonResponse getAllPosts(){
        return new JsonResponse(true, "All posts have been retrieved.", this.postService.getAllPosts());
    }

    @GetMapping("post/{userId}")
    public JsonResponse getPostsByUserId(@PathVariable Integer userId){
        JsonResponse jsonResponse;

        List<Post> userPostList = this.postService.getPostsByUserId(userId);

        if(userPostList != null){
            jsonResponse = new JsonResponse(true, "Posts for user with user ID: " + userId + " obtained.", userPostList);
        }else{
            jsonResponse = new JsonResponse(false, "No posts obtained.", null);
        }
        return jsonResponse;
    }

    @PostMapping("post")
    public JsonResponse createPost(@RequestBody Post post){
        JsonResponse jsonResponse;
        this.postService.addNewPost(post);

        if(post != null){
            jsonResponse = new JsonResponse(true, "Post has been created.", post);
        }
        else {
            jsonResponse = new JsonResponse(false, "Post was empty or could not be created.", null);
        }
        return jsonResponse;
    }
}
