package com.energy.repository;

import com.energy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDao")
@Transactional
public interface UserDao extends JpaRepository<User, Integer> {
    @Query("from User where username= :username")
    User selectUserByName(@Param("username")String username);


    // @Modifying
    // @Query("update User where userId= :userid")

}
