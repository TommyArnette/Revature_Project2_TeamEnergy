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


  // @Modifying
  // @Query("update User where userId= :userid")

    @Modifying
    @Query("UPDATE User u SET u.userFirstName = :userFirstName WHERE u.userId= :userId")
    void updateUserFirstName(@Param("userId") Integer userId, @Param("userFirstName") String userFirstName);
}
