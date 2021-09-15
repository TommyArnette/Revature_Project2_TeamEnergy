package com.revature.repository;

import com.revature.models.Post;
import com.revature.models.User;
import org.hibernate.SessionFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Used to create an interface for to perform CRUD operations for Post objects.
 *
 * This interface extends the JpaRepository and references the Post class and the datatype of its primary key.
 *
 * Utilizes the Spring WebMVC @Repository and @Transactional annotations.
 *
 * An additional method was written to return a list of all Posts for a specific userId.
 */
@Repository("postDao")
@Transactional
public interface PostDao extends JpaRepository<Post, Integer> {
    /**
     * Returns a list of Posts for a specific userId.
     *
     * @param userIdFk  The userId of the user for which we want to return their List of Posts
     * @return          List of Posts for a specific userId
     */
    List<Post> findPostByUserIdFk(Integer userIdFk);
}
