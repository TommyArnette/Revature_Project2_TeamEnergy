package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * The Likes model class represents a "Like" that a user can indicate on another user's post.
 * This class contains an auto-incrementing primary key likeId, a reference to the user first name,
 * a reference to the user last name, a foreign key reference to the userId, and a foreign key reference
 * to the postId.
 *
 * Creates a Like object.
 *
 * The Lombok dependency eliminates boilerplate getter and setter and constructor code with the @Data, @NoArgsConstructor,
 * and @AllArgsConstructor annotations.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "likes")
public class Likes {
    @Id
    @Column(name="like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer likeId;

    @Column(name="userF")
    private String userF;

    @Column(name="userL")
    private String userL;

    @Column(name="user_Id_like")
    private Integer userIdL;

    @Column(name="post_id_fk")
    private Integer postIdFk;
}
