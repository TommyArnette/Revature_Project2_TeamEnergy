package com.energy.service;

import com.energy.models.User;

public interface UserService {

    public User login(User user);
    public void registerNewUser(User user);
    public User updateUserInfo(User user);

}
