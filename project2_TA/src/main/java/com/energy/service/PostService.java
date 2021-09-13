
package com.energy.service;

import com.energy.models.PostImage;
import com.energy.repository.PostDao;
import com.energy.repository.PostImageDao;
import com.energy.repository.UserDao;
import com.energy.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("postService")
public class PostService {
    private PostDao postDao;
    private PostImageDao postImageDao;

    UserDao userDao;

    @Autowired
    public PostService(PostDao postDao){
        this.postDao = postDao;
    }

    public List<Post> selectAllPosts(){return this.postDao.findAll();}

    public Post createNewPost(Post post){
        return this.postDao.save(post);
    }

    public Post selectPostsByUserId(Integer useridFk){
        return this.postDao.findPostByUserIdFk(useridFk).orElse(null);
    }

    public Post getPostByID(Integer id){
        return this.postDao.findById(id).orElse(null);
    }

    public PostImage createNewPostImage(PostImage postImage){
        return this.postImageDao.save(postImage);
    }

}