package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The User model class represents a user that is registered with the Team Energy Social Network.
 * Creates a User object.
 *
 * The user is assigned an auto-incrementing primary key userId, must create their own username and password,
 * inputs their first name (userFirstName) and last name (userLastName), enters their email (userEmail),
 * can enter a profile description (optional), and may upload a profile image (optional).
 *
 * The Lombok dependency eliminates boilerplate getter and setter and constructor code with the @Data, @NoArgsConstructor,
 * and @AllArgsConstructor annotations.
 *
 * Additional constructors were created in this model class to provide improved flexibility when creating functionality.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
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

    @Column(name="user_email", unique = true, nullable = false, columnDefinition = "varchar(100)")
    private String userEmail;

    @Column(name="user_profile_description", columnDefinition = "varchar(300)")
    private String userProfileDescription;

    @Column(name="user_profile_image")
    private String userProfileImage;

    @Column(name = "reset_token")
    private String resetToken;

    /**
     * Constructor with username and password parameters.
     *
     * @param username The unique username of the user
     * @param password The password of the user
     * @return         Returns a User object
     */

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String userFirstName, String userLastName, String userEmail, String userProfileDescription, String userProfileImage) {
        this.username = username;
        this.password = password;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userProfileDescription = userProfileDescription;
        this.userProfileImage = userProfileImage;
    }

    public User(String username, String password, String userFirstName, String userLastName, String userEmail) {
        this.username = username;
        this.password = password;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
    }

    /**
     * Created a new Constructor for testing methods
     *
     * @param userId
     * @param username
     * @param password
     * @param userFirstName
     * @param userLastName
     * @param userEmail
     * @param userProfileDescription
     * @param userProfileImage
     */
    public User(Integer userId, String username, String password, String userFirstName, String userLastName, String userEmail, String userProfileDescription, String userProfileImage) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userProfileDescription = userProfileDescription;
        this.userProfileImage = userProfileImage;
    }
}
