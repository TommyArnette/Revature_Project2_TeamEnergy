package com.energy.dao;

import com.energy.models.Post;

import java.util.List;

public interface PostsDao {
    public void addNewPost(Post post);

    List<Post> getUserPostList(Integer userIdFk);
    List<Post> selectAllPosts();
}
