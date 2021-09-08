package com.energy.dao;

import com.energy.models.Post;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository("postDao")
@Transactional
public class PostsDaoImpl implements PostsDao{
    private SessionFactory sessionFactory;

    @Autowired
    public PostsDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addNewPost(Post post) {
        this.sessionFactory.getCurrentSession().save(post);
    }

    @Override
    public List<Post> getUserPostList(Integer userIdFk) {
        return this.sessionFactory.getCurrentSession().createQuery("from Posts where userIdFk = '" + userIdFk + "'", Post.class).list();
    }

    @Override
    public List<Post> selectAllPosts() {
       return this.sessionFactory.getCurrentSession().createQuery("from Posts", Post.class).list();
    }
}
