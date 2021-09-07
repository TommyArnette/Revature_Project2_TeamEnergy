package com.energy.dao;

import com.energy.models.Post;
import com.energy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PostsDaoImpl implements PostsDao{
    @Override
    public void addNewPost(Post post) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        session.save(post);

        tx.commit();
    }

    @Override
    public List<Post> getUserPostList(Integer userIdFk) {
        Session session = HibernateUtil.getSession();
        return session.createQuery("from Posts where userIdFk = '" + userIdFk + "'", Post.class).list();
    }

    @Override
    public List<Post> selectAllPosts() {
       Session session = HibernateUtil.getSession();
       return session.createQuery("from Posts", Post.class).list();
    }
}
