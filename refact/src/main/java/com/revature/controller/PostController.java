package com.revature.controller;

import com.revature.models.JsonResponse;
import com.revature.models.Post;
import com.revature.models.User;
import com.revature.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController("postController")
@RequestMapping(value="api")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("posts")
    public JsonResponse getAllPosts(){
        return new JsonResponse(true, "All posts obtained.", this.postService.selectAllPosts());
    }

    @PostMapping("posts")
    public JsonResponse createNewPost(HttpSession session, @RequestBody Post post){
        JsonResponse jsonResponse;

        Post newPost = this.postService.createNewPost(post);

        if(newPost != null){
            jsonResponse = new JsonResponse(true, "Post created.", newPost);
        }
        else{
            jsonResponse = new JsonResponse(false, "No post created.", null);
        }
        return jsonResponse;
    }

    @GetMapping("posts/{userIdFk}")
    public JsonResponse getPostsByUserId(@PathVariable Integer userIdFk){
        return new JsonResponse(true, "User " + userIdFk + " posts obtained.", this.postService.selectAllPosts());
    }
}
