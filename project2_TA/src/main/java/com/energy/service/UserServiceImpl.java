package com.energy.service;

import com.energy.dao.UserDao;
import com.energy.models.User;

public class UserServiceImpl implements UserService{
    /* Added 9/4 10:35 PM Tommy A.*/
    UserDao userDao;

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
}
