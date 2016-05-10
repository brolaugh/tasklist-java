package com.brolaugh.itg.tasklist.graphical;

import com.brolaugh.itg.tasklist.database.Task;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
            this.setMinWidth(100);

            Text title = new Text(this.task.getTitle());
            title.setFont(Font.font("Times New Roman", 35));
            title.prefHeight(50);
            this.setTop(title);

            Text t = new Text(this.task.getDescription());
            t.setWrappingWidth(450);
            this.setCenter(t);
            this.widthProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    t.setWrappingWidth(newValue.doubleValue() - 50);
                }
            });

            this.setBottom(new Text("Updated " + this.task.getCurrentStatus().getStamp() + " by " + this.task.getCurrentStatus().getUser()));

        }
        public void changeTask(Task newTask){
            this.task = newTask;
            Text title = new Text(this.task.getTitle());
            title.setFont(Font.font("Times New Roman", 35));
            title.prefHeight(50);
            this.setTop(title);

            Text t = new Text(this.task.getDescription());
            t.setWrappingWidth(200);
            this.setCenter(t);


            this.setBottom(new Text("Updated " + this.task.getCurrentStatus().getStamp() + " by " + this.task.getCurrentStatus().getUser()));
        }
}
