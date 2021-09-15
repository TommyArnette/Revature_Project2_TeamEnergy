package com.revature.repository;

import com.revature.models.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Used to create an interface for to perform CRUD operations for PostImage objects.
 *
 * This interface extends the JpaRepository and references the PostImage class and the datatype of its primary key.
 *
 * Utilizes the Spring WebMVC @Repository and @Transactional annotations.
 */

@Repository("postImageDao")
@Transactional
public interface PostImageDao extends JpaRepository<PostImage, Integer> {
}