package com.cibt.chatapp.handler;

import java.util.ArrayList;
import java.util.List;

public class ClientHandler {
    private List<Client> clients = new ArrayList<>();

    public void addClient(Client client) {
        clients.add(client);
    }

    public Client getByUserName(String userName) {
        for (Client c : clients) {
            if (c.getUser().getUserName().equals(userName)) {
                return c;
            }
        }
        return null;
    }

    public List<Client> getClients() {
        return clients;
    }

    public boolean removeClient(Client client) {
        return clients.remove(client);
    }
}