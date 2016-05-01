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
        this.setCenter(new Text(this.item.getDescription()));
        //this.setLeft(new Icon);
        //item.print();


    }
    public void changeStatus(Status s){
        this.item.addStatus(s);
        this.getStyleClass().add(item.getStatuses().getLast().getLevel().getCSSClass());
        //Add other stuff
    }
    public Status getStatusLevel(){
        return this.item.getStatuses().getLast();
    }
    public Task getTask(){
        return this.item;
    }
}
