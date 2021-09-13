package com.energy.service;

import com.energy.repository.UserDao;
import com.energy.models.User;

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
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao){this.userDao = userDao;}

    public List<User> selectAllUsers(){return this.userDao.findAll();}

    public User selectUserById(Integer userId){return this.userDao.findById(userId).orElse(null);}

    public User selectUserByUsername(String username){return this.userDao.selectUserByName(username);}

    public User registerNewUser(User user) throws javax.mail.MessagingException{
        User temp = selectUserByUsername(user.getUsername());

        if(temp==null){
            user.setPassword(new BasicPasswordEncryptor().encryptPassword(user.getPassword()));
            // sendWelcomeEmail(user);
            return this.userDao.save(user);

        }
        return null;
    }

    //need to test update
    //public User updateUserInfo(User user){

    //     this.userDao.updateById(user.getUserId());
    //    return user;
    //  }

    public User login(User user){

        User currentUser = userDao.selectUserByName(user.getUsername());

        if(currentUser == null) {
            return null;
        }else{
            if(new BasicPasswordEncryptor().checkPassword(user.getPassword(), currentUser.getPassword())) {
                // System.out.println(user.getPassword());
                // System.out.println(currentUser.getPassword());
                return currentUser;
            }

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
                return new PasswordAuthentication("mikearcherdev@gmail.com", "360deltafox");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("mikearcherdev@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(user.getUserEmail()));
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


