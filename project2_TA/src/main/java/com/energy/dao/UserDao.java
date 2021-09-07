package com.energy.dao;

import com.energy.models.User;

import java.util.List;

public interface UserDao {
    void addNewUser(User user);
    void updateUserInfo(User user);

    User selectUserById(Integer userId);
    User getUser(User user);
    List<User> selectAllOtherUsers(User user);
}
