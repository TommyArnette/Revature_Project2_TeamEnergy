package com.energy.dao;

import com.energy.models.Posts;

import java.util.List;

public interface PostsDao {
    public void addNewPost(Posts posts);

    List<Posts> userPostList(Integer userIdFk);
    List<Posts> selectAllPosts();
}
