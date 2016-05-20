package com.brolaugh.itg.tasklist.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBSetup {
    private String host = "home.rickfo.se";
    private String database = "brolaugh_tasklist";
    private String user = "brolaugh";
    private String password = "rickardh";
    private String driver = "com.mysql.jdbc.Driver";
    protected Connection dbc;
    //A class for storing database credentials to make git commits and pushes less worrying
    public DBSetup(){

        try{
            Class.forName(driver).newInstance();
            dbc = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database,user,password);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void close(){
        try {
            dbc.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
