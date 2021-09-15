package com.revature.repository;

import com.revature.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Used to create an interface for to perform CRUD operations for Like objects.
 *
 * This interface extends the JpaRepository and references the Like class and the datatype of its primary key.
 *
 * Utilizes the Spring WebMVC @Repository and @Transactional annotations.
 */
@Repository("likeDao")
@Transactional
public interface LikeDao extends JpaRepository<Likes, Integer> { }
