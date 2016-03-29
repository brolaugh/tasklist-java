package com.brolaugh.itg.tasklist.database;

import java.sql.Timestamp;
import java.util.Date;

public class Status {
    private int id;
    private StatusLevel level;
    private String user;
    private Timestamp stamp;

    public Status(int id, String user, Timestamp stamp, StatusLevel level){
        this.id = id;
        this.level = level;
        this.user = user;
        this.stamp = stamp;
    }
    public Status(StatusLevel level, String user){
        this.level = level;
        this.user = user;
        this.stamp = new Timestamp(new Date().getTime());
    }

    public void print(){
        System.out.println("\t" + user + " changed status to " + level.getPlainText());
    }



    public StatusLevel getLevel() {
        return level;
    }

    public Timestamp getStamp() {
        return stamp;
    }

    public String getUser() {
        return user;
    }

    public int getId() {
        return id;
    }
}
