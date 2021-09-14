package com.revature.repository;

import com.revature.models.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("postImageDao")
@Transactional
public interface PostImageDao extends JpaRepository<PostImage, Integer> {
}