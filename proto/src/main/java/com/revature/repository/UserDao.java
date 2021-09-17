package com.revature.repository;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Used to create an interface for to perform CRUD operations for User objects.
 *
 * This interface extends the JpaRepository and references the User class and the datatype of its primary key.
 *
 * Utilizes the Spring WebMVC @Repository and @Transactional annotations.
 *
 * Additional methods were created using the Spring Data Annotation @Query to specify a query sent to the database.
 */

@Repository("userDao")
@Transactional
public interface UserDao extends JpaRepository<User, Integer> {
    /**
     * Selects a User based on their username.
     *
     * @param username  Username of the User for which we are searching.
     * @return          Returns the User object found by this query.
     */
    @Query("from User where username= :username")
    User selectUserByName(@Param("username")String username);

    /**
     * Returns a User based on their email.
     *
     * @param userEmail The email associated with the User object.
     * @return          Returns the User object found by this query.
     */
    @Query("from User where userEmail= :userEmail")
    User findByEmail(@Param("userEmail") String userEmail);

    /**
     * Returns a User based on the specific password reset token that was generated and matches the data in the database.
     *
     * @param resetToken    The password reset token that is generated when a User attempts to reset their password.
     * @return              Returns the User object associated with this specific password reset token.
     */
    @Query("from User where resetToken= :resetToken")
    User findByResetToken(@Param("resetToken")String resetToken);
}
