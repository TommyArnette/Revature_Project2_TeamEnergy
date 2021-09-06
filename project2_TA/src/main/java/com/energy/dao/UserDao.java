package com.energy.dao;

import com.energy.models.User;

import java.util.List;

public interface UserDao {
    public void addNewUser(User user);
    public void updateUserInfo(User user);

    User selectUserById(Integer userId);
    User selectUserByName(String username);
    User getUser(User user);
    List<User> selectAllUsers();
}
