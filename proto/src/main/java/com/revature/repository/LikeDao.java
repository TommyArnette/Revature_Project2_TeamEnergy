package com.revature.repository;

import com.revature.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("likeDao")
@Transactional
public interface LikeDao extends JpaRepository<Likes, Integer> {

}
