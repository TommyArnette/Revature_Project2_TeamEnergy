package com.revature.service;

import com.revature.models.Comments;
import com.revature.repository.CommentsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service used to communicate with the CommentsDao and operate on data retrieved from the database.
 */
@Service("commentsService")
public class CommentsService {
    private CommentsDao commentsDao;

    @Autowired
    public CommentsService(CommentsDao commentsDao){
        this.commentsDao = commentsDao;
    }

    /**
     * Creates a comment
     *
     * @param comments  message created by user and assigned to a post
     * @return          returns a Comments object
     */
    public Comments createComment(Comments comments){
        return this.commentsDao.save(comments);
    }
}
