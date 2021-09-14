package com.revature.service;

import com.revature.models.Likes;
import com.revature.models.Post;
import com.revature.repository.LikeDao;
import com.revature.repository.PostDao;
import com.revature.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("likeService")
public class LikeService {
    private LikeDao likeDao;

    @Autowired
    public LikeService(LikeDao likeDao){this.likeDao = likeDao;}

    public Likes createLike(Likes likes){return this.likeDao.save(likes);}
}
