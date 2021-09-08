package com.energy.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name="user_username", unique = true, nullable = false, columnDefinition = "varchar(50)")
    private String username;

    @Column(name="user_password", nullable = false, columnDefinition = "varchar(50)")
    private String password;

    @Column(name="user_first_name", nullable = false, columnDefinition = "varchar(50)")
    private String userFirstName;

    @Column(name="user_last_name", nullable = false, columnDefinition = "varchar(50)")
    private String userLastName;

    @Column(name="user_email", nullable = false, columnDefinition = "varchar(100)")
    private String userEmail;

    @Column(name="user_profile_description", columnDefinition = "varchar(300)")
    private String userProfileDescription;

    @Column(name="user_profile_image")
    private String userProfileImage;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Post> userPostList = new ArrayList<>();
}
