package com.i2i.bicell.config;

public class config{
    private static String FilePath;

    public static String get_FilePath(){
        return FilePath;
    }
    public static void set_FilePath(String FilePath){
        config.FilePath = FilePath;
    }
}