package com.cibt.chatapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.cibt.chatapp.Service.UserService;
import com.cibt.chatapp.Service.Impl.UserServiceImpl;
import com.cibt.chatapp.handler.ClientHandler;
import com.cibt.chatapp.handler.RequestListener;

public class App {
    public static void main(String[] args) {
        int port = 9000;
        UserService userService = new UserServiceImpl();
        ClientHandler handler = new ClientHandler();

        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server running at port " + server.getLocalPort());
            while (true) {
                Socket socket = server.accept();
                System.out.println("Connection request from: " + socket.getInetAddress().getHostAddress());
                RequestListener listener = new RequestListener(socket);
                listener.setUserService(userService);
                listener.setHandler(handler);
                listener.start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}