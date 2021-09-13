package com.energy.repository;

import com.energy.models.Post;
import com.energy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository("postDao")
@Transactional
public interface PostDao extends JpaRepository<Post, Integer> {

    Optional<Post> findPostByUserIdFk(Integer userIdFk);
}