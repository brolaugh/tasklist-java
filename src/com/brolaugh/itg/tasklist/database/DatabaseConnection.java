package com.brolaugh.itg.tasklist.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class DatabaseConnection extends DBSetup{

    public LinkedList<Task> getTasks(){
        LinkedList<Task> retval = new LinkedList<Task>();
        try{
            PreparedStatement stmt = dbc.prepareStatement("SELECT id as id, title as title, description as description FROM task");
            ResultSet result = stmt.executeQuery();
            result.next();
            while(result.next()){
                retval.add(new Task(result.getInt("id"), result.getString("title"), result.getString("description")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return retval;
    }
    public LinkedList<Status> getStatuses(int id){
        LinkedList<Status> retval = new LinkedList<Status>();
        try{
            PreparedStatement stmt = dbc.prepareStatement("SELECT status.id as status_id, user as user, stamp as stamp, status_level.id as level_id, plain_text as plain_text, style_class as style_class FROM status INNER JOIN status_level ON status.status_level=status_level.id WHERE task = ?");
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            result.next();
            while(result.next()){
                retval.add(new Status(result.getInt("status_id") ,result.getString("user"),result.getTimestamp("stamp"), new StatusLevel(result.getInt("level_id"), result.getString("plain_text"), result.getString("style_class"))));
            }
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return retval;
    }
    public void getStatuses(LinkedList<Task> tasks){
        try{
            PreparedStatement stmt = dbc.prepareStatement("SELECT status.id as status_id, user, stamp, status_level.id as level_id, plain_text, style_class FROM status INNER JOIN status_level ON status.status_level=status_level.id WHERE task = ?");
            for(int i = 0; i < tasks.size(); i++){
                stmt.setInt(1, tasks.get(i).getId());
                ResultSet result = stmt.executeQuery();
                result.next();
                while(result.next()){
                    tasks.get(i).addStatus(
                            new Status(result.getInt("status_id") ,result.getString("user"),result.getTimestamp("stamp"),
                                new StatusLevel(result.getInt("level_id"), result.getString("plain_text"), result.getString("style_class"))
                    ));
                }
            }
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public LinkedList<StatusLevel>  getStatusLevels(){
            LinkedList<StatusLevel> retval = new LinkedList<StatusLevel>();
            try{
                PreparedStatement stmt = dbc.prepareStatement("SELECT * FROM status_level");
                ResultSet result = stmt.executeQuery();
                while(result.next()){
                    retval.add(new StatusLevel(result.getInt("id"), result.getString("plain_text"), result.getString("style_class")));
                }
                stmt.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        return retval;
    }
}
