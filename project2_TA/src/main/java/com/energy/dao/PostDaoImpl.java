package com.energy.dao;

import com.energy.models.Post;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("postsDao")
@Transactional
public class PostDaoImpl implements PostDao {
    private SessionFactory sessionFactory;

    @Autowired
    public PostDaoImpl(SessionFactory sessionFactory){

        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addNewPost(Post post) {

        this.sessionFactory.getCurrentSession().save(post);
    }

    @Override
    public List<Post> userPostList(Integer userIdFk) {
        return sessionFactory.getCurrentSession().createQuery("from Posts where userId = '" + userIdFk + "'", Post.class).list();
    }

    @Override
    public List<Post> selectAllPosts() {
        return sessionFactory.getCurrentSession().createQuery("from Posts", Post.class).list();
    }
}
