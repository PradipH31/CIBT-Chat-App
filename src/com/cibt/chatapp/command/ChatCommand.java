package com.cibt.chatapp.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import com.cibt.chatapp.handler.Client;
import com.cibt.chatapp.handler.ClientHandler;


public abstract class ChatCommand {
    protected Client client;
    protected ClientHandler handler;
    protected PrintStream out;
    protected BufferedReader reader;

    public abstract void execute(String[] tokens) throws IOException;

    public void initialize(Client client, ClientHandler handler, PrintStream out, BufferedReader reader) {
        this.client = client;
        this.handler = handler;
        this.out = out;
        this.reader = reader;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @param handler the handler to set
     */
    public void setHandler(ClientHandler handler) {
        this.handler = handler;
    }

    /**
     * @param out the out to set
     */
    public void setPrinter(PrintStream out) {
        this.out = out;
    }

    /**
     * @param reader the reader to set
     */
    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }
}