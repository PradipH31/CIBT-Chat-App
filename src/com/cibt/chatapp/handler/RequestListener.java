package com.cibt.chatapp.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;

import com.cibt.chatapp.Service.UserService;
import com.cibt.chatapp.command.ChatCommand;
import com.cibt.chatapp.command.ChatCommandFactory;
import com.cibt.chatapp.data.Broadcaster;
import com.cibt.chatapp.entity.User;

public class RequestListener extends Thread {
    Socket socket;
    BufferedReader reader = null;
    PrintStream out = null;
    private UserService userService;
    int id = 1;
    private Client client;
    private ClientHandler handler;

    public RequestListener(Socket socket) throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream());
    }

    /**
     * @param handler the handler to set
     */
    public void setHandler(ClientHandler handler) {
        this.handler = handler;
    }

    /**
     * @param userService the userService to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run() {
        try {
            process();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void menu() {
        out.println("Hello " + socket.getInetAddress().getHostAddress());
        out.println("1. Register");
        out.println("2. Login");
        out.println("3. Exit");
        out.println("Enter your choice:");
    }

    private void process() throws IOException {
        while (true) {
            menu();
            String choice = reader.readLine();
            switch (choice) {
            case "1":
                register();
                break;
            case "2":
                login();
                break;

            default:
                socket.close();
                break;
            }
        }
    }

    private void register() throws IOException {
        User user = new User();
        out.println("User Registration");
        out.println("Enter user-name:");
        user.setUserName(reader.readLine());
        out.println("Enter password:");
        user.setPassword(reader.readLine());
        user.setId(id);
        user.setJoinDate(new Date());
        user.setStatus(true);
        userService.add(user);
    }

    private void login() throws IOException {
        out.println("User Login");
        out.println("Enter user-name:");
        String userName = reader.readLine();
        out.println("Enter password:");
        String password = reader.readLine();
        User user = userService.login(userName, password);
        if (user != null) {
            client = new Client(socket, user);
            handler.addClient(client);
            Broadcaster.broadcastMessage(client, handler, "User " + client.getUser().getUserName() + " has joined the chat room");
            chat();
        } else {
            out.println("Invalid credentials");
        }
    }

    private void chat() throws IOException {
        while (true) {
            out.print("(me):>");
            String line = reader.readLine();
            String[] tokens=line.split(";;");
            ChatCommand cmd=ChatCommandFactory.get(tokens[0]);;
            cmd.initialize(client, handler, out, reader);
            cmd.execute(tokens);
        }
    }


}