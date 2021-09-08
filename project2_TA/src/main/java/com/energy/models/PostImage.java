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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Posts posts;

    @Column(name="post_image_url")
    private String postImageUrl;
}
