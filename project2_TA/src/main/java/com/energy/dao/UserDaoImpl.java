package com.energy.dao;

import com.energy.models.User;
import com.energy.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class UserDaoImpl implements UserDao {
    static UserDao userDao;

    public static UserDao getInstance(){
        if(userDao == null){
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

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

    /* Just added this method 9/4 10:30 PM. This is similar to JDBC ? parameter (:username & :password) */
    @Override
    public User getUser(User user) {
        Session session = HibernateUtil.getSession();

        TypedQuery query = session.createQuery("from User where username = :username AND password = :password", User.class);
        query.setParameter(0, user.getUsername());
        query.setParameter(1, user.getPassword());

        User currentUser = (User) query.getSingleResult();

        return currentUser;
    }

    @Override
    public List<User> selectAllUsers() {
        Session session = HibernateUtil.getSession();
        return session.createQuery("from User", User.class).list();
    }

}
