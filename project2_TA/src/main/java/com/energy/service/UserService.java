package com.energy.service;

import com.energy.models.User;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.util.List;

public interface UserService {
    public User login(User user);
    public void registerNewUser(User user) throws MessagingException;
    public User updateUserInfo(User user);
    public List<User> getFriends(User user);
    public void sendWelcomeEmail(User user) throws MessagingException;
}
