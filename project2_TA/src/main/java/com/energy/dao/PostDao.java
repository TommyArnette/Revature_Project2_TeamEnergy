package com.energy.dao;

import com.energy.models.Post;

import java.util.List;

public interface PostDao {
    public void addNewPost(Post post);

    List<Post> userPostList(Integer userIdFk);
    List<Post> selectAllPosts();
}
