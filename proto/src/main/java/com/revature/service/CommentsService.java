package com.revature.service;

import com.revature.models.Comments;
import com.revature.repository.CommentsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentsService")
public class CommentsService {
    private CommentsDao commentsDao;

    @Autowired
    public CommentsService(CommentsDao commentsDao){
        this.commentsDao = commentsDao;
    }

    public Comments createComment(Comments comments){
        return this.commentsDao.save(comments);
    }
}
