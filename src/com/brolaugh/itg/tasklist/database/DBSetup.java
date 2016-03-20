package com.brolaugh.itg.tasklist.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBSetup {
    private String host = "";
    private String database = "";
    private String user = "";
    private String password = "";
    private String driver = "com.mysql.jdbc.Driver";
    protected Connection dbc;

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
