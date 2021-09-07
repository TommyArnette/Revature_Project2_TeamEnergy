package com.energy.service;

import com.energy.models.User;

import java.util.List;

public interface UserService {

    public User login(User user);
    public void registerNewUser(User user);
    public User updateUserInfo(User user);
    public List<User> getFriends(User user);

}
