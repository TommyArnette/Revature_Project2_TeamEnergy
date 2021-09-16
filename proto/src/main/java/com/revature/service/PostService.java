package com.revature.service;

import com.revature.models.Post;
import com.revature.models.PostImage;
import com.revature.models.User;
import com.revature.repository.PostDao;
import com.revature.repository.PostImageDao;
import com.revature.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This PostService class contains all of the methods utilized by the Team Energy Social Network related to Post objects.
 *
 * References the PostDao to obtain relevant information from the PostDao layer.
 */
@Service("postService")
public class PostService {
    private PostDao postDao;

    /**
     * Autowired this reference to the PostImageDao to provide Dependency Injection to Spring
     */
    @Autowired
    private PostImageDao postImageDao;

    /**
     * Provided constructor injection to Spring to manage this dependency
     * @param postDao   References the postDao layer
     */
    @Autowired
    public PostService(PostDao postDao){
        this.postDao = postDao;
    }

    /**
     * Obtains a List of all Posts
     *
     * @return  List of all Posts
     */
    public List<Post> selectAllPosts(){return this.postDao.findAll();}


    public List<Post>selectPostMinMax(Integer min, Integer max){

        return this.postDao.findPostBypostIdBetween(min,max);
    }


    /**
     * Obtains a List of all Posts specific to a userId
     *
     * @param userIdFk  references the User object for which we want the List of Posts
     * @return  Returns a List of Posts specific to the userId
     */
    public List<Post> selectPostByUserId(Integer userIdFk){return this.postDao.findPostByUserIdFk(userIdFk);}

    /**
     * Returns a newly created Post object
     *
     * @param post  A new Post object
     * @return      Returns a Post object
     */
    public Post createNewPost(Post post){
        return this.postDao.save(post);
    }

    /**
     * Returns/Creates a new image associated with a post
     *
     * @param postImage PostImage object passed to method
     * @return          Returns a PostImage object
     */
    public PostImage createNewPostImage(PostImage postImage){
        return this.postImageDao.save(postImage);
    }
}
