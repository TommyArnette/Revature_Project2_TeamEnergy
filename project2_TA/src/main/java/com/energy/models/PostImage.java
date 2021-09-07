package com.energy.models;

import javax.persistence.*;

@Entity
@Table(name="postImages")
public class PostImage {
    @Id
    @Column(name="post_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postImageId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Post post;

    @Column(name="post_image_url")
    private String postImageUrl;

    public PostImage(Integer postImageId, Post post, String postImageUrl) {
        this.postImageId = postImageId;
        this.post = post;
        this.postImageUrl = postImageUrl;
    }

    public PostImage() {
    }

    public PostImage(Post post, String postImageUrl) {
        this.post = post;
        this.postImageUrl = postImageUrl;
    }

    public Integer getPostImageId() {
        return postImageId;
    }

    public void setPostImageId(Integer postImageId) {
        this.postImageId = postImageId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

    @Override
    public String toString() {
        return "PostImage{" +
                "postImageId=" + postImageId +
                ", post=" + post +
                ", postImageUrl='" + postImageUrl + '\'' +
                '}';
    }
}
