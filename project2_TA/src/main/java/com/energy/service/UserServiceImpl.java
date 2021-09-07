package com.energy.service;

import com.energy.dao.UserDao;
import com.energy.dao.UserDaoImpl;
import com.energy.models.User;

import java.util.List;

public class UserServiceImpl implements UserService{

    UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public User login(User user) {
        User currentUser = userDao.getUser(user);

        if(currentUser.getUsername() == null){
            return null;
        }

        if(!currentUser.getPassword().equals(user.getPassword())){
            return null;
        }

        return currentUser;
    }

    @Override
    public void registerNewUser(User user) {
        userDao.addNewUser(user);
    }

    @Override
    public User updateUserInfo(User user) {
        userDao.updateUserInfo(user);
        return user;
    }

    @Override
    public List<User> getFriends(User user) {
        return userDao.selectAllOtherUsers(user);
    }
}
