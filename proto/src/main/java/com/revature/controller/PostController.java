package com.revature.controller;

import com.revature.models.JsonResponse;
import com.revature.models.Post;
import com.revature.models.PostImage;
import com.revature.models.User;
import com.revature.service.PostService;
import com.revature.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController("postController")
@RequestMapping(value="api")
@CrossOrigin(value = "http://localhost:4200/", allowCredentials = "true")
public class PostController {
    private PostService postService;

    @Autowired
    private S3Service s3Service;

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

        User user = (User) session.getAttribute("loggedInUser");

        if(user != null){
            post.setUserIdFk(user.getUserId());
            post.setUser(user);

            Post newPost = this.postService.createNewPost(post);
            if(newPost != null){
                jsonResponse = new JsonResponse(true, "Post created.", newPost);
            }else{
                jsonResponse = new JsonResponse(false, "No post created.", null);
            }
        }
        else{
            return jsonResponse = new JsonResponse(false, "You need to be logged in.", null);
        }

        return  jsonResponse;
    }

    @GetMapping("posts/{userIdFk}")
    public JsonResponse getPostsByUserId(@PathVariable Integer userIdFk){
        if(userIdFk != null){
            return new JsonResponse(true, "User " + userIdFk + " posts obtained.", this.postService.selectPostByUserId(userIdFk));
        }else{
            return new JsonResponse(false, "User ID " + userIdFk + " does not exist.", null);
        }
    }

    @PostMapping("postImage")
    public JsonResponse createNewPostImage(@RequestBody PostImage postImage){
        String url = this.s3Service.getURL(postImage.getPostImageName());
        PostImage pi = this.postService.createNewPostImage(url,postImage);
        return new JsonResponse(true,"postImage created",pi);
    }
}
