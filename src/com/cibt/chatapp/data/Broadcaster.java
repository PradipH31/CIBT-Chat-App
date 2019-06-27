package com.cibt.chatapp.data;

import java.io.IOException;
import java.io.PrintStream;

import com.cibt.chatapp.handler.Client;
import com.cibt.chatapp.handler.ClientHandler;

public class Broadcaster {
    public static void broadcastMessage(Client client, ClientHandler handler, String msg) throws IOException {
        for (Client c : handler.getClients()) {
            if (!c.equals(client)) {
                PrintStream ps = new PrintStream(c.getSocket().getOutputStream());
                ps.println(msg);
            }
        }
    }
}