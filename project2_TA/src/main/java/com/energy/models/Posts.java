package com.energy.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="posts")
public class Posts {

    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name="post_created_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a")
    private Date postCreatedDate;

    @Column(name="post_message")
    private String postMessage;

    @Column(name="user_id_fk")
    private Integer userIdFk;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    public Posts() {
    }

    public Posts(Integer postId, Date postCreatedDate, String postMessage, Integer userIdFk, User user) {
        this.postId = postId;
        this.postCreatedDate = postCreatedDate;
        this.postMessage = postMessage;
        this.userIdFk = userIdFk;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "postId=" + postId +
                ", postCreatedDate=" + postCreatedDate +
                ", postMessage='" + postMessage + '\'' +
                ", userIdFk=" + userIdFk +
                ", user=" + user +
                '}';
    }
}
