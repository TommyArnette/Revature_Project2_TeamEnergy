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

@Service("postService")
public class PostService {
    private PostDao postDao;

    @Autowired
    private PostImageDao postImageDao;

    @Autowired
    public PostService(PostDao postDao){
        this.postDao = postDao;
    }

    public List<Post> selectAllPosts(){return this.postDao.findAll();}

    public List<Post> selectPostByUserId(Integer userIdFk){return this.postDao.findPostByUserIdFk(userIdFk);}

    public Post createNewPost(Post post){
        return this.postDao.save(post);
    }

    public PostImage createNewPostImage(String url, PostImage postImage){
        Post post = this.postDao.findById(postImage.getPost_fk()).orElse(null);
        if(post != null){
            postImage.setPostImageUrl(url);

            return this.postImageDao.save(postImage);
        }

        return null;
    }
}
