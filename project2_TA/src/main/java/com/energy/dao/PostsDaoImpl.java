package com.energy.dao;

import com.energy.models.Posts;
import com.energy.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("postsDao")
@Transactional
public class PostsDaoImpl implements PostsDao{
    private SessionFactory sessionFactory;

    @Autowired
    public PostsDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addNewPost(Posts posts) {
        this.sessionFactory.getCurrentSession().save(posts);
    }

    @Override
    public List<Posts> userPostList(Integer userIdFk) {
        return sessionFactory.getCurrentSession().createQuery("from Posts where", Posts.class).list();
    }

    @Override
    public List<Posts> selectAllPosts() {
        return sessionFactory.getCurrentSession().createQuery("from Posts", Posts.class).list(); //NOT COMPLETE
    }
}
