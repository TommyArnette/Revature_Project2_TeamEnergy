package com.revature.service;

import com.revature.models.Likes;
import com.revature.models.Post;
import com.revature.repository.LikeDao;
import com.revature.repository.PostDao;
import com.revature.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Using the Spring Stereotype @Service
 *
 * This class handles our Like Service layer and references the LikeDao to obtain information.
 */
@Service("likeService")
public class LikeService {
    private LikeDao likeDao;

    /**
     * Autowired this constructor to advise Spring to handle Dependency Injection
     * @param likeDao   Passes the information obtained from the LikeDao to this constructor
     */
    @Autowired
    public LikeService(LikeDao likeDao){this.likeDao = likeDao;}

    /**
     * This method creates a Like object
     *
     * @param likes Refers to a new Like object
     * @return      Returns a Like objects
     */
    public Likes createLike(Likes likes){return this.likeDao.save(likes);}

    public void deleteLike(Integer likeId){
        Likes like = this.likeDao.findById(likeId).orElse(null);

        if(like == null){
            return;
        }

        this.likeDao.delete(like);
    }
}
