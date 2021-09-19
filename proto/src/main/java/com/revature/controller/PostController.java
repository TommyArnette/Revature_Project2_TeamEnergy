package com.revature.controller;

import com.revature.models.JsonResponse;
import com.revature.models.Post;
import com.revature.models.PostImage;
import com.revature.models.User;
import com.revature.service.PostService;
import com.revature.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Controller for Like HTTP methods and endpoints.
 * @RestController is used to imply JSON request body language
 * @CrossOrigin is used to solve browser CORS error messaging
 * Initial endpoint "api" defined by the @RequestMapping annotation
 */
@RestController("postController")
@RequestMapping(value="api")
@CrossOrigin(value = "http://localhost:4200/", allowCredentials = "true")
public class PostController {
    private PostService postService;

    /**
     * References the S3Service class to allow image uploading functionality.
     */
    @Autowired
    private S3Service s3Service;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    /**
     * Gets all Post objects regardless of User.
     *
     * @return  returns a List of Posts
     * */
    @GetMapping("posts")
    public JsonResponse getAllPosts(){
        return new JsonResponse(true, "All posts obtained.", this.postService.selectAllPosts());
    }

    /**
     * Used to get posts between the specified range of numbers.
     * Ex. Page 2 will list posts 21 through 40.
     *
     * @param page  number representing the page a user is on to view posts
     * @return      returns a JsonResponse
     */
    @GetMapping("posts/pages/{page}")
    public JsonResponse getPostBetween(@PathVariable Integer page){
        return new JsonResponse(true, "Posts from page: " + page, this.postService.selectPostMinMax(page));
    }

    @GetMapping("posts/count")
    public JsonResponse getPostCount(){
        return new JsonResponse(true, "Number of posts fetched", this.postService.selectPostCount());
    }

    /**
     * Used to create a new Post object and associate it with a specific User object.
     *
     * @param post      Passes a new Post object to the method (information obtained from request body)
     * @return          returns a JsonResponse message
     */
    @PostMapping("posts")
    public JsonResponse createNewPost(@RequestBody Post post){
        JsonResponse jsonResponse;

        User user = post.getUser();
        //User user = (User) session.getAttribute("loggedInUser");
        //This commented code was breaking the front end. User session info is stored in front end.

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

    /**
     * Obtains all Post objcts specific to a User Id
     *
     * @param userIdFk  User Id passed to method to obtain all Posts associated with this User object
     * @return          returns JsonResponse message
     */
    @GetMapping("posts/{userIdFk}")
    public JsonResponse getPostsByUserId(@PathVariable Integer userIdFk){
        if(userIdFk != null){
            return new JsonResponse(true, "User " + userIdFk + " posts obtained.", this.postService.selectPostByUserId(userIdFk));
        }else{
            return new JsonResponse(false, "User ID " + userIdFk + " does not exist.", null);
        }
    }

    /**
     * Used to create new PostImage object. Associates a newly uploaded image with a Post object.
     *
     * @param postImage passes the PostImage object to the method
     * @return          returns JsonResponse message
     */
    @PostMapping("postImage")
    public JsonResponse createNewPostImage(@RequestBody PostImage postImage){
        PostImage pi = this.postService.createNewPostImage(postImage);
        return new JsonResponse(true,"postImage created",pi);
    }
}
