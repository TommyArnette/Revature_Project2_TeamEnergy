package com.revature.repository;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("userDao")
@Transactional
public interface UserDao extends JpaRepository<User, Integer> {
    @Query("from User where username= :username")
    User selectUserByName(@Param("username")String username);

    @Query("from User where userEmail= :userEmail")
    User findByEmail(@Param("userEmail") String userEmail);//add

    @Query("from User where resetToken= :resetToken")
    User findByResetToken(@Param("resetToken")String resetToken);//add

}
