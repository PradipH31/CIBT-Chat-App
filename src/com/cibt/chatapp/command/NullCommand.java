package com.cibt.chatapp.command;

import java.io.IOException;

public class NullCommand extends ChatCommand {

    @Override
    public void execute(String[] tokens) throws IOException {
        out.println("Invalid Command");
    }

}