package com.cibt.chatapp.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.cibt.chatapp.Service.UserService;
import com.cibt.chatapp.entity.User;

public class UserServiceImpl implements UserService {
    private List<User> users = new ArrayList<>();

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User login(String userName, String password) {
        for (User u : users) {
            if (u.getUserName().equals(userName) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public boolean updateBalance(User user, double amount, int choice) {
        for (User u : users) {
            if (u.equals(user)) {
                if (choice == 0) {
                    u.setBalance(u.getBalance() - amount);
                } else {
                    u.setBalance(u.getBalance() + amount);
                }
            }
        }
        return false;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }
}