package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
This is all added to try to allow Users capability to add comments to posts.
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Integer commentId;

    @Column(name = "comment_message", nullable = false)
    private String commentMessage;

    @Column(name = "user_firstname")
    private String userFirstN;

    @Column(name = "user_lastname")
    private String userLastN;

    @Column(name = "user_id_comments")
    private Integer userIdC;

    @Column(name = "post_foreign")
    private Integer postForeign;
}
