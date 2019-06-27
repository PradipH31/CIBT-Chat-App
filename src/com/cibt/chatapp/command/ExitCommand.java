package com.cibt.chatapp.command;

import java.io.IOException;

import com.cibt.chatapp.data.Broadcaster;

public class ExitCommand extends ChatCommand {

    @Override
    public void execute(String[] tokens) throws IOException {
        Broadcaster.broadcastMessage(client, handler,
                "User " + client.getUser().getUserName() + " has left the chat room");
        handler.removeClient(client);
        client.getSocket().close();
    }

}