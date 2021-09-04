package com.energy.models;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name="user_username", unique = true, nullable = false, columnDefinition = "varchar(50)")
    private String username;

    @Column(name="user_userpassword", nullable = false, columnDefinition = "varchar(50)")
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
    private byte[] userProfileImage;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Posts> userPostList = new ArrayList<>();

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Integer userId, String username, String password, String userFirstName, String userLastName, String userEmail, String userProfileDescription, byte[] userProfileImage, List<Posts> userPostList) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userProfileDescription = userProfileDescription;
        this.userProfileImage = userProfileImage;
        this.userPostList = userPostList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserProfileDescription() {
        return userProfileDescription;
    }

    public void setUserProfileDescription(String userProfileDescription) {
        this.userProfileDescription = userProfileDescription;
    }

    public byte[] getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(byte[] userProfileImage) {
        this.userProfileImage = userProfileImage;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userProfileDescription='" + userProfileDescription + '\'' +
                ", userProfileImage=" + Arrays.toString(userProfileImage) +
                ", userPostList=" + userPostList +
                '}';
    }
}
