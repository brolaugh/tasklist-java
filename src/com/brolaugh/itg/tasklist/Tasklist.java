package com.brolaugh.itg.tasklist;


import com.brolaugh.itg.tasklist.database.DatabaseConnection;
import com.brolaugh.itg.tasklist.database.StatusLevel;
import com.brolaugh.itg.tasklist.database.Task;

import com.brolaugh.itg.tasklist.graphical.TaskItem;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.LinkedList;


public class Tasklist extends Application {
    private DatabaseConnection dbc = new DatabaseConnection();
    private ObservableList<TaskItem> listitem = FXCollections.observableArrayList();
    private LinkedList<Task> tasks;
    public Tasklist(){
        tasks = dbc.getTasks();
        dbc.getStatuses(tasks);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //The top menu
        final Menu filterMenu = new Menu("Filter");
        for(StatusLevel sl : dbc.getStatusLevels()){
            filterMenu.getItems().add(new MenuItem(sl.getPlainText(), new CheckBox()));
        }

        final Menu optionsMenu = new Menu("Options");
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(filterMenu,optionsMenu);

        //Loading tasks into the application
        ListView<TaskItem> list = new ListView<>();

        ObservableList<TaskItem> listitem = FXCollections.observableArrayList();
        //Adding tasks to the viewable list
        for(Task task: tasks){
            listitem.add(new TaskItem(task));
        }
        list.setItems(listitem);


        //Scene building
        StackPane root = new StackPane();
        Scene mainScene = new Scene(root, 1280, 720);
        BorderPane rootLayout = new BorderPane();

        //Adding CSS to UI
        mainScene.getStylesheets().add("/com/brolaugh/itg/tasklist/tasklist.css");

        //Putting everything together
        rootLayout.setTop(menuBar);
        rootLayout.setCenter(list);
        root.getChildren().add(rootLayout);

        //Final touches
        primaryStage.setTitle("Tasklist");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args){
        new Tasklist();
        launch(args);

    }
}
