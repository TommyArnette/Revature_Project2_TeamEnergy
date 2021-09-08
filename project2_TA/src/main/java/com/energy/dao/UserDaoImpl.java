package com.energy.dao;

import com.energy.models.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {
    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addNewUser(User user) {
        this.sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void updateUserInfo(User user) {
        this.sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public User selectUserById(Integer userId) {
        return sessionFactory.getCurrentSession().get(User.class, userId);
    }

    @Override
    public User selectUserByName(String username) {
        return sessionFactory.getCurrentSession().createQuery("from User where username = '" + username + "'", User.class).getSingleResult();
    }

    @Override
    public User getUser(User user) {
        return null; // NOT COMPLETE
    }

    @Override
    public List<User> selectAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User", User.class).list();
    }

}
