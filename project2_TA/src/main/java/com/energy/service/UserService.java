package com.energy.service;

import com.energy.dao.UserDao;
import com.energy.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService {
    UserDao userDao;

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

    public void registerNewUser(User user) {
        this.userDao.addNewUser(user);

        /*
        *
        * //encripts password using jasypt
        String passEncr="revmedia";
        //password from user
        String pass= user.getPassword();

        BasicTextEncryptor TEncryptor = new BasicTextEncryptor();
        TEncryptor.setPassword(passEncr);
        //encripts  the user password
        String encrpass = TEncryptor.encrypt(pass);
        //adds the encripted password to user
        user.setPassword(encrpass);


        //sends password and email to the user
        String usNa = user.getUsername();
        String ema = user.getUserEmail();

        //user email
        String to = ema;

        // company email
        String from = "ryan50534535@gmail.com";
        final String username = "ryan50534535@gmail.com";//your gmail username
        final String password = "rafasdasad";//your gmail password

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
            message.setSubject("Welcome to the The Best Media App");

            // Put the content of your message
            message.setText("Hello there. Your user has been successfully created, your Username is: " +
                    usNa+" and your Password is: "+pass);

            // Send message
            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        userDao.addNewUser(user);*/
    }

    public User updateUserInfo(User user){
        this.userDao.updateUserInfo(user);
        return user;
    }

}
