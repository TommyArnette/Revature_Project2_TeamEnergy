package com.energy.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="postImages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostImage {
    @Id
    @Column(name="post_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postImageId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Post post;

    @Column(name="post_image_url")
    private String postImageUrl;


    public PostImage(Post post, String postImageUrl) {
        this.post = post;
        this.postImageUrl = postImageUrl;
    }
}
