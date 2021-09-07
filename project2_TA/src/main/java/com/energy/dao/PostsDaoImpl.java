package com.energy.dao;

import com.energy.models.Posts;
import com.energy.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PostsDaoImpl implements PostsDao{
    @Override
    public void addNewPost(Posts posts) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        session.save(posts);

        tx.commit();
    }

    @Override
    public List<Posts> userPostList(Integer userIdFk) {
        Session session = HibernateUtil.getSession();
        return session.createQuery("from Posts where userIdFk = '" + userIdFk + "'", Posts.class).list();
    }

    @Override
    public List<Posts> selectAllPosts() {
       Session session = HibernateUtil.getSession();
       return session.createQuery("from Posts", Posts.class).list();

    }
}
