package com.cloud.designpattern.strategymodel.example;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rayss
 */
@SuppressWarnings("all")
public enum CommandEnum {


    SELECT("select", "com.cloud.designpattern.strategymodel.example.SelectCommand")
    , ADD("add", "com.cloud.designpattern.strategymodel.example.AddCommand")
    , ABORTED("abort", "com.cloud.designpattern.strategymodel.example.AbortCommand");
    private String command;
    private String clazz;
 
    public static Map<String, String> getAllClazz() {
        Map<String, String> map = new HashMap<>(8);
        for (CommandEnum commandEnum : CommandEnum.values()) {
            map.put(commandEnum.getCommand(), commandEnum.getClazz());
        }
        return map;
    }
 
    public String getCommand() {
        return command;
    }
 
    CommandEnum(String command, String clazz) {
        this.command = command;
        this.clazz = clazz;
    }
 
    public void setCommand(String command) {
        this.command = command;
    }
 
    public String getClazz() {
        return clazz;
    }
 
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
 
}

