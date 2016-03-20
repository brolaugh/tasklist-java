package com.brolaugh.itg.tasklist.database;

import java.util.LinkedList;
class Task {
    private int id;
    private String title;
    private String description;
    private LinkedList<Status> statuses;

    public Task(int id){
        //GET task from DB
    }
    public Task(int id, String title){
        this.id = id;
        this.title = title;
        this.description = "";
    }
    public Task(int id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
        statuses = new LinkedList<Status>();
    }
    public Task(int id, String title, String description, LinkedList<Status> statuses){
        this.id = id;
        this.title = title;
        this.description = description;
        this.statuses = statuses;
    }
    public Task(int id, String title, LinkedList<Status> statuses){
        this.id = id;
        this.title = title;
        this.statuses = statuses;
    }
    public void print(){
        System.out.println("Id: " + id);
        System.out.println("Title: " + title);
        if(!description.contains("")){
            System.out.println("Description"+ description);
        }
        for(Status s: statuses){
            s.print();
        }
        System.out.println("____________________________");


    }
    public void addStatus(Status s){
        statuses.add(s);
    }



    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public LinkedList<Status> getStatuses(){

        return statuses;
    }
    public Status getCurrentStatus(){
        return statuses.getLast();
    }
}
