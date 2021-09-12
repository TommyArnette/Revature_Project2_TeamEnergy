package com.revature.service;

import com.revature.models.Post;
import com.revature.models.User;
import com.revature.repository.PostDao;
import com.revature.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("postService")
public class PostService {
    private PostDao postDao;

    UserDao userDao;

    @Autowired
    public PostService(PostDao postDao){
        this.postDao = postDao;
    }

    public List<Post> selectAllPosts(){return this.postDao.findAll();}

    public List<Post> selectPostByUserId(Integer userIdFk){return this.postDao.findPostByUserIdFk(userIdFk);}

    public Post createNewPost(Post post){
        return this.postDao.save(post);
    }
}
