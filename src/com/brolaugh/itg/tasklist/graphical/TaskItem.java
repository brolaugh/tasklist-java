package com.brolaugh.itg.tasklist.graphical;


import com.brolaugh.itg.tasklist.database.Status;
import com.brolaugh.itg.tasklist.database.StatusLevel;
import com.brolaugh.itg.tasklist.database.Task;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TaskItem extends BorderPane{
    private Task item;

    public TaskItem(Task item){
        this.item = item;
        //this.setLeft(new ImageView(new Image()));
        Text t = new Text(this.item.getTitle());
        t.setTextAlignment(TextAlignment.JUSTIFY);
        this.setTop(new Text(this.item.getTitle()));

        if(this.item.getDescription().length() >= 70){
            this.setCenter(new Text(this.item.getDescription().substring(0, 70) + "..."));
        }else{
            this.setCenter(new Text(this.item.getDescription()));
        }

    }
    public Status getStatusLevel(){
        return this.item.getStatuses().getLast();
    }
    public Task getTask(){
        return this.item;
    }
}
