package com.cibt.chatapp.Service;

import java.util.List;

import com.cibt.chatapp.entity.User;

public interface UserService {
    void add(User user);

    List<User> getUsers();

    User login(String userName, String password);

    boolean updateBalance(User user, double amount, int choice);
}