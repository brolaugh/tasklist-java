package com.brolaugh.itg.tasklist;

import com.brolaugh.itg.tasklist.database.DatabaseConnection;
import com.brolaugh.itg.tasklist.database.Task;

import java.util.LinkedList;

public class Tasklist {
    public Tasklist(){


    }
    public static void main(String[] args){
        DatabaseConnection d = new DatabaseConnection();
        LinkedList<Task> tasks = d.getTasks();
        d.getStatuses(tasks);
        for(Task t : tasks){
            t.print();
        }

    }
}
