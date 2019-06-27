package com.cibt.chatapp.handler;

import java.net.Socket;

import com.cibt.chatapp.entity.User;

public class Client {
    private Socket socket;
    private User user;

    public Client(Socket socket, User user) {
        this.socket = socket;
        this.user = user;
    }

    /**
     * @return the socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * @param socket the socket to set
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
}