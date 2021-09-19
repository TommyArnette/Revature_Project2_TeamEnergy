package com.revature.service;

import com.revature.models.User;
import com.revature.repository.UserDao;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * Service associated with User service methods.
 * References the UserDao.
 */
@Service("userService")
public class UserService {
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao){this.userDao = userDao;}

    /**
     * Obtains a List of all Users registered with the Team Energy Social Network.
     * @return  returns a List of type User
     */
    public List<User> selectAllUsers(){return this.userDao.findAll();}

    /**
     * Obtains a User specific to the userId passed to the method.
     * This method utilizes the Optional<T> method. If no User objects are found, "null" is returned.
     *
     * @param userId userId associated with a registered User
     * @return       returns a User object
     */
    public User selectUserById(Integer userId){return this.userDao.findById(userId).orElse(null);}

    /**
     * Obtains a User specific to the username passed to the method.
     *
     * @param username  username associated with a registered User
     * @return          returns the specific User object
     */
    public User selectUserByUsername(String username){return this.userDao.selectUserByName(username);}

    /**
     * Obtains a User specific to the userEmail information provided to the method.
     *
     * @param userEmail email associated with a registered User.
     * @return          returns the specific User object
     */
    public User selectUserByEmail(String userEmail){return  this.userDao.findByEmail(userEmail);}

    /**
     * Generates a password reset token necessary for successful password reset.
     *
     * @param user      User object passed to the method
     * @return          returns a User object
     * @throws MessagingException   throws an exception if WHY???
     */
    public User generateLink(User user) throws MessagingException {
        user.setResetToken(UUID.randomUUID().toString());
        emailSenduserLink(user);
        return this.userDao.save(user);
    }

    /**
     * Obtains a User specific to the password reset token associated with the object.
     *
     * @param resetToken   reset token is created and associated with a User when they attempt to reset password
     * @return             returns User object that matches the password reset token
     */
    public User selectByToken(String resetToken){return this.userDao.findByResetToken(resetToken);}

    /**
     * Saves the User object when they have successfully reset their password
     *
     * @param user  User that is changing their password
     * @return      returns User object with successful password reset
     */
    public User saveUserWithNewPassword(User user){
        user.setPassword(new BasicPasswordEncryptor().encryptPassword(user.getPassword()));
        user.setResetToken(null);
        return this.userDao.save(user);
    }

    /**
     * Used to register a new user to the Team Energy Social Network.
     * This method automatically encrypts the password entered by the User object.
     *
     * @param user references a new User object
     * @return     returns a new User object
     * @throws javax.mail.MessagingException    throws an exception if an invalid email is found
     */
    public User registerNewUser(User user) throws javax.mail.MessagingException{
        User temp = selectUserByUsername(user.getUsername());

        if(temp==null){
            user.setPassword(new BasicPasswordEncryptor().encryptPassword(user.getPassword()));
           // sendWelcomeEmail(user);
            return this.userDao.save(user);
        }
        return null;
    }

    /**
     * This method is used to update the profile information associated with a User.
     * This can be used to update multiple pieces of User information at once (EX. First name, Last name, email) or
     * one piece of information in a single transaction.
     * Returns a null value if the user does not exist.
     * NOTE: This method DOES NOT update a User's password.
     *
     * @param user  passed a registered User object
     * @return      returns a User object (same User object with updated information)
     */
    public User updateUser(User user){
        User u = this.userDao.findById(user.getUserId()).orElse(null);

        if(u != null){
            u.setUsername(user.getUsername());
            u.setUserFirstName(user.getUserFirstName());
            u.setUserLastName(user.getUserLastName());
            u.setUserEmail(user.getUserEmail());
            u.setUserProfileDescription(user.getUserProfileDescription());
            u.setUserProfileImage(user.getUserProfileImage());
            return this.userDao.save(u);
        }
        return null;
    }

    /**
     * Method is used to log a User into the Team Energy Social Network.
     * The method checks that a username matches data in the database. If the username matches, then it checks to see
     * if the password matches the database. If the username does not match, it returns null.
     * If the username matches, but the password does not match, it returns null.
     * If both the username and password matches, the user successfully logs in.
     *
     * @param user  passes the User object attempting ot login
     * @return      returns a User object
     */
    public User login(User user){
        User currentUser = userDao.selectUserByName(user.getUsername());

        if(currentUser == null) {
            return null;
        }else{
            if(new BasicPasswordEncryptor().checkPassword(user.getPassword(), currentUser.getPassword())) {
                return currentUser;
            }
        }
        return null;
    }

    /**
     * Used to send a welcome email to the User that successfully registered with Team Energy Social Network.
     *
     * @param user                              The User object successfully registered is passed to this method.
     * @throws javax.mail.MessagingException    Triggered if an invalid email is provided
     */
    public void sendWelcomeEmail(User user) throws javax.mail.MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mikearcherdev@gmail.com", "360deltafox");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("mikearcherdev@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(user.getUserEmail()));
        message.setSubject("Welcome to the Team Energy Social Network App!");

        String msg = "Your account has been created!\n" +
                "Username: " + user.getUsername() + "\n" +
                "Password: " + user.getPassword();

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }

    /**
     * Used to send a User object a link to reset their password after indicating they need to reset their password.
     *
     * @param user  passed the User object that needs to reset their password
     */
    public void emailSenduserLink(User user){
        //user email
        String appUrl = "http://localhost:4200/forgot/" + user.getResetToken();
        String to = user.getUserEmail();

        // company email
        String from = "revbook@revbook.com";
        final String username = "project2smtpemail@gmail.com";//your gmail username
        final String password = "revbook123";//your gmail password
        //using gmail to send mail
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // get the session object
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // set subject in the email sent
            message.setSubject("Revbook: Forgot Password?");

            // Put the content of your message
            message.setText("Please follow this link to reset your password: " + appUrl);

            // Send message
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
