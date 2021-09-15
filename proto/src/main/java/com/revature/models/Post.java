package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The Post model class represents the information associated with a social media post made by a user.
 * The object information includes an auto-incrementing primary key postId, the date the post was created,
 * the message contained within the post, a reference to the userId of the user that posted, and it contains two lists.
 *
 * Creates a Post object.
 *
 * These lists represent the likes (multiple likes can be associated with one post) assigned to the post, and the
 * posts (zero, one, or many posts may be uploaded per post) associated with the post.
 *
 * The Lombok dependency eliminates boilerplate getter and setter and constructor code with the @Data, @NoArgsConstructor,
 * and @AllArgsConstructor annotations.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name="post_created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private Date postCreatedDate;

    @Column(name="post_message")
    private String postMessage;

    @Column(name="user_id_fk")
    private Integer userIdFk;

    @JoinColumn(name = "user", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(mappedBy = "postIdFk", cascade = CascadeType.ALL)
    private List<Likes> likes;

    @OneToMany(mappedBy = "post_fk", cascade = CascadeType.ALL)
    private List<PostImage> postImageList;
}
