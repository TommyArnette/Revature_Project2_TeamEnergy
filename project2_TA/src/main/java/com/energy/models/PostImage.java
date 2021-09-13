package com.energy.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="postImages")
public class PostImage {

    @Id
    @Column(name="post_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postImageId;

    @Column(name="post_image_url")
    private String postImageUrl;

    @Column(name="post_image_name")
    private String postImageName;

    @Column(name="post_fk")
    private Integer post_fk;
}