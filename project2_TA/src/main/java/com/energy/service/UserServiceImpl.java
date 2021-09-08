package com.energy.service;

import com.energy.dao.UserDao;
import com.energy.dao.UserDaoImpl;
import com.energy.models.User;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.List;
import java.util.Properties;

@Service("userService")
public class UserServiceImpl implements UserService{

    UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
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

    @Override
    public void registerNewUser(User user) throws MessagingException {
        user.setPassword(new BasicPasswordEncryptor().encryptPassword(user.getPassword()));
        userDao.addNewUser(user);
        sendWelcomeEmail(user);
    }

    @Override
    public User updateUserInfo(User user) {
        userDao.updateUserInfo(user);
        return user;
    }

    @Override
    public List<User> selectAll() {
        return this.userDao.selectAll();
    }

    @Override
    public User selectUserById(Integer userId) {
        return this.userDao.selectUserById(userId);
    }

    @Override
    public List<User> getFriends(User user) {
        return userDao.selectAllOtherUsers(user);
    }

    @Override
    public void sendWelcomeEmail(User user) throws MessagingException {
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
