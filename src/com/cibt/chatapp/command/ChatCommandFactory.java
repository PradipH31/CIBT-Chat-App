package com.cibt.chatapp.command;

public class ChatCommandFactory{
    public static ChatCommand get(String param){
        if(param.equalsIgnoreCase("list")){
            return new ListCommand();
        }else if(param.equalsIgnoreCase("exit")){
            return new ExitCommand();
        }
        return new NullCommand();
    }
}