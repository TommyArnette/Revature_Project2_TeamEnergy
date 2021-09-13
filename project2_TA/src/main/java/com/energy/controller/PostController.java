package com.energy.controller;

import com.energy.models.JsonResponse;
import com.energy.models.Post;
import com.energy.models.PostImage;
import com.energy.models.User;
import com.energy.service.PostService;
import com.energy.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@RestController("postController")
@RequestMapping(value="api")
public class PostController {
    private PostService postService;
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

    @PostMapping("postsImage/{postID}")
    public JsonResponse createNewPostImage(@PathVariable Integer postID, @RequestBody PostImage postImage){
        JsonResponse jsonResponse;
        Post post = this.postService.getPostByID(postID);

        if(post != null){
            postImage.setPost(post);
            PostImage newPostImage = this.postService.createNewPostImage(postImage);
            if( newPostImage != null){
                jsonResponse = new JsonResponse(true,"postImage created",newPostImage);
            }else{
                jsonResponse = new JsonResponse(false, "No postImage created",null);
            }
        }else {
            return new JsonResponse(false, "No post select",null);
        }
        return jsonResponse;
    }


}
