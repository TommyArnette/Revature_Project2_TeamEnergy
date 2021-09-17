package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The PostImage model class contains the information associated with an image that is uploaded to a post.
 *
 * Creates a PostImage object.
 *
 * There is an auto-incrementing primary key postImageId, a String representing the image URL, a String containing
 * the name of the image, and a foreign key reference to the post.
 *
 * The Lombok dependency eliminates boilerplate getter and setter and constructor code with the @Data, @NoArgsConstructor,
 * and @AllArgsConstructor annotations.
 */

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