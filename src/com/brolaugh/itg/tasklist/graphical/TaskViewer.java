package com.brolaugh.itg.tasklist.graphical;

import com.brolaugh.itg.tasklist.database.Task;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
public class TaskViewer extends BorderPane {
        private Task task;
        public TaskViewer(Task task){
            this.task = task;
            this.__construct();
        }
        //Basicly a constuct that is called when the TaskViewer is created and also changes task
        private void __construct(){
            this.setPrefWidth(500);
            this.setMinWidth(100);
            //Creates the title and adds it to the top of the node
            Text title = new Text(this.task.getTitle());
            title.setFont(Font.font("Times New Roman", 35));

            Text t = new Text(this.task.getDescription());
            t.setWrappingWidth(450);
            title.setWrappingWidth(450);

            //When the width of the window changes the width of all text also changes with it
            this.widthProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    t.setWrappingWidth(newValue.doubleValue() - 50);
                    title.setWrappingWidth(newValue.doubleValue() - 50);
                }
            });

            this.setTop(title);
            this.setCenter(t);

            //Gets an image and then puts it into the UI
            Image image = new Image("file:///" + System.getProperty("user.dir") + "\\update.png");
            System.out.println(System.getProperty("user.dir"));
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(50);
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    //Not yet implemented: When the image is clicked on a status update menu should appear
                }
            });

            BorderPane footer = new BorderPane();
            footer.setCenter(new Text("Updated " + this.task.getCurrentStatus().getStamp() + " by " + this.task.getCurrentStatus().getUser()));
            footer.setRight(imageView);
            this.setBottom(footer);

        }
        public void changeTask(Task newTask){
            this.task = newTask;
            this.__construct();
        }
}
