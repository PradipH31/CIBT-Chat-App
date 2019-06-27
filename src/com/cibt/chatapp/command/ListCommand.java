package com.cibt.chatapp.command;

import java.io.IOException;

import com.cibt.chatapp.handler.Client;

public class ListCommand extends ChatCommand {

    @Override
    public void execute(String[] tokens) throws IOException {
        StringBuilder content = new StringBuilder();
        for (Client c : handler.getClients()) {
            content.append(c.getUser().getUserName());
            if (c.equals(client)) {
                content.append("(me)");
            }
            content.append("\r\n");
        }
        out.println(content.toString());
    }

}