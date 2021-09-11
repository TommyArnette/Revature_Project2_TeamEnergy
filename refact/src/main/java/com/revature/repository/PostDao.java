package com.revature.repository;

import com.revature.models.Post;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("postDao")
@Transactional
public interface PostDao extends JpaRepository<Post, Integer> {
}
