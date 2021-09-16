package com.revature.repository;

import com.revature.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("commentsDao")
@Transactional
public interface CommentsDao extends JpaRepository<Comments, Integer> {
}
