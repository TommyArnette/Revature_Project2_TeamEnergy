package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

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
    //cascade = CascadeType.ALL,

    @Column(name="post_id_fk")
    private Integer postIdFk;
}
