package com.brolaugh.itg.tasklist.graphical;


import com.brolaugh.itg.tasklist.database.Status;
import com.brolaugh.itg.tasklist.database.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
        System.out.println(item.getStatuses().getLast().getLevel().getCSSClass());
        this.getStyleClass().add(item.getStatuses().getLast().getLevel().getCSSClass());
    }
    public void changeStatus(Status s){
        this.item.addStatus(s);
        this.getStyleClass().add(item.getStatuses().getLast().getLevel().getCSSClass());
        //Add other stuff
    }
}
