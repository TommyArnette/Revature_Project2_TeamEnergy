package com.energy.service;

import com.energy.dao.UserDao;
import com.energy.models.User;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
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

@Service("userService")
public class UserService {
    UserDao userDao;

    @Autowired
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public List<User> selectAllUsers(){
        return this.userDao.selectAllUsers();
    }

    public User selectUserById(Integer userId){
        return this.userDao.selectUserById(userId);
    }

    public User selectUserByUsername(String username){
        return this.userDao.selectUserByName(username);
    }

    public void registerNewUser(User user) throws javax.mail.MessagingException {
        user.setPassword(new BasicPasswordEncryptor().encryptPassword(user.getPassword()));
        userDao.addNewUser(user);
        sendWelcomeEmail(user);
    }

    public List<User> getFriends(User user) {
        return userDao.selectAllOtherUsers(user);
    }

    public User updateUserInfo(User user){
        this.userDao.updateUserInfo(user);
        return user;
    }

    public User login(User user) {
        User currentUser = userDao.getUser(user);

        if(currentUser == null) {
            return null;
        }

        if(new BasicPasswordEncryptor().checkPassword(user.getPassword(), currentUser.getPassword())) {
            return currentUser;
        }

        return null;
    }

    public void sendWelcomeEmail(User user) throws javax.mail.MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mikearcherdev@gmail.com", "Mike1455.");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("mikearcherdev@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse("michael.archer@g.austincc.edu"));
        message.setSubject("Welcome to Reimbursement App!");

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

}
