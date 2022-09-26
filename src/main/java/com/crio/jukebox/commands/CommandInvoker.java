package com.crio.jukebox.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInvoker {

    public static final Map<String, ICommand> commandMap = new HashMap<>();

    //Register the command to hashmap
    public void register(String commandName, ICommand command){
        commandMap.put(commandName, command);
    }
    
    //get the register command
    private ICommand get(String commandName){
        return commandMap.get(commandName);
    }

    //Execute the register command
    public void executeCommand(String commandName, List<String> tokens){
        ICommand command = commandMap.get(commandName);
        /*if(command == null){
            throw new RuntimeE
        }*/
        command.execute(tokens);
    }
}
