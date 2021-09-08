package com.energy.dao;

import com.energy.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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
    public List<User> selectAll() {
        return sessionFactory.getCurrentSession().createQuery("from User", User.class).list();
    }

    @Override
    public User selectUserById(Integer userId) {
        return this.sessionFactory.getCurrentSession().get(User.class, userId);

    }

    /* Just added this method 9/4 10:30 PM. This is similar to JDBC ? parameter (:username & :password) */
    @Override
    public User getUser(User user) {
       // Session session = HibernateUtil.getSession();

      //  TypedQuery query = session.createQuery("from User where username = :username", User.class);
       // query.setParameter("username", user.getUsername());

      //  User currentUser = (User) query.getSingleResult();

        return null;
    }

    @Override
    public List<User> selectAllOtherUsers(User user) {
        return this.sessionFactory.getCurrentSession().createQuery("from User where userId != " + user.getUserId(), User.class).list();
    }

}
