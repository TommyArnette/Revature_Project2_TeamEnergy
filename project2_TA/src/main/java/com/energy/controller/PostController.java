package com.energy.controller;

import com.energy.models.JsonResponse;
import com.energy.models.Post;
import com.energy.models.PostImage;
import com.energy.models.User;
import com.energy.service.PostService;
import com.energy.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@RestController("postController")
@RequestMapping(value="api")
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

        if(user!=null) {
            post.setUserIdFk(user.getUserId());
            post.setUser(user);
            Post newPost = this.postService.createNewPost(post);

            if (newPost != null) {
                jsonResponse = new JsonResponse(true, "Post created.", newPost);
            } else {
                jsonResponse = new JsonResponse(false, "No post created.", null);
            }
        }else {
            return new JsonResponse(false,"You need to be login",null);
        }
        return jsonResponse;
    }

    @GetMapping("posts/{userIdFk}")
    public JsonResponse getPostsByUserId(@PathVariable Integer userIdFk){
        return new JsonResponse(true, "User " + userIdFk + " posts obtained.", this.postService.selectPostsByUserId(userIdFk));
    }


    @PostMapping("postImage")
    public JsonResponse createNewPostImage(@RequestBody PostImage postImage){
        String url = this.s3Service.getURL(postImage.getPostImageName());
        PostImage pi = this.postService.createNewPostImage(url,postImage);
        return new JsonResponse(true,"postImage created",pi);
    }

    @GetMapping("postImage")
    public JsonResponse getALLPostImage(){
        return new JsonResponse(true, "All posts obtained.", this.postService.getAllPostImages());
    }


}
