package com.energy.dao;

import com.energy.models.User;
import com.energy.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public void addNewUser(User user) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        session.save(user);

        tx.commit();
        session.close();
    }

    @Override
    public void updateUserInfo(User user) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        session.update(user);

        tx.commit();
        session.close();
    }

    @Override
    public User selectUserById(Integer userId) {
        Session session = HibernateUtil.getSession();

        User user = session.get(User.class, userId);

        session.close();

        return user;
    }

    @Override
    public User selectUserByName(String username) {
        Session session = HibernateUtil.getSession();
        return session.createQuery("from User where username = '" + username + "'", User.class).getSingleResult();
    }

    @Override
    public List<User> selectAllUsers() {
        Session session = HibernateUtil.getSession();
        return session.createQuery("from User", User.class).list();
    }

}
