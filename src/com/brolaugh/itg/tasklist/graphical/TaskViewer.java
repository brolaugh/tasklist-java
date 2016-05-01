package com.brolaugh.itg.tasklist.graphical;

import com.brolaugh.itg.tasklist.database.Task;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by hannes.kindstrommer on 2016-04-26.
 */
public class TaskViewer extends BorderPane {
        private Task task;
        public TaskViewer(Task task){
            this.task = task;
            this.__construct();
        }
        /*public TaskEditor(){
            this.__construct();
            this task = new Task();
        }*/
        private void __construct(){
            this.setPrefWidth(500);
            this.setMinWidth(200);

            Text title = new Text(this.task.getTitle());
            title.setFont(Font.font("Times New Roman", 35));
            title.prefHeight(50);
            this.setTop(title);

            this.setCenter(new StatusHistory(task.getStatuses()));


            this.setBottom(new Text(this.task.getDescription()));

        }
        public void changeTask(Task newTask){
            this.task = newTask;
            Text title = new Text(this.task.getTitle());
            title.setFont(Font.font("Times New Roman", 35));
            title.prefHeight(50);
            this.setTop(title);

            this.setCenter(new StatusHistory(task.getStatuses()));


            this.setBottom(new Text(this.task.getDescription()));
        }
}
