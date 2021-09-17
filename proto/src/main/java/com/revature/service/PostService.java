package com.revature.service;

import com.revature.models.Post;
import com.revature.models.PostImage;
import com.revature.models.User;
import com.revature.repository.PostDao;
import com.revature.repository.PostImageDao;
import com.revature.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
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

    /**
     * Used to select a specific number of posts between the min and max range. Used for pagination to only fetch a
     * specific amount of posts at once.
     *
     * @param page  page the user is on in the feed when viewing posts
     * @return      returns a list of posts
     */
    public List<Post> selectPostMinMax(Integer page){
        double totalPost =postDao.findAll().size();
        double min ,max;
        //double round=totalpost/20;
        //double num = (double) (Math.ceil(round));
        max = (page*20)-20;
        max=totalPost-max;
        min = (page*20);
        min=totalPost-min+1;
        if(min<=1){
            min=1;
            int value1 = (int)min;
            int value2 = (int)max;
            List<Post> nlist=this.postDao.findPostBypostIdBetween(value1,value2);
            Collections.sort(nlist,
                    Comparator.comparingInt(Post::getPostId).reversed());
            return nlist;
        }else{
            int value1 = (int)min;
            int value2 = (int)max;
            List <Post> nlist=this.postDao.findPostBypostIdBetween(value1,value2);
            Collections.sort(nlist,
                    Comparator.comparingInt(Post::getPostId).reversed());
            return nlist;
        }
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
