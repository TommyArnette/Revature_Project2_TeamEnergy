package com.energy.service;

import com.energy.dao.PostDao;
import com.energy.models.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("postService")
public class PostService {
    PostDao postDao;

    public PostService(PostDao postDao){
        this.postDao = postDao;
    }

    public List<Post> getAllPosts(){
        return this.postDao.selectAllPosts();
    }

    public List<Post> getPostsByUserId(Integer userId){
        return this.postDao.userPostList(userId);
    }

    public void addNewPost(Post post){
        this.postDao.addNewPost(post);
    }
}
