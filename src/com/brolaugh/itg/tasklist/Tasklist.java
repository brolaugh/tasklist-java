package com.brolaugh.itg.tasklist;


import com.brolaugh.itg.tasklist.database.DatabaseConnection;
import com.brolaugh.itg.tasklist.database.StatusLevel;
import com.brolaugh.itg.tasklist.database.Task;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.LinkedList;


public class Tasklist extends Application {
    private DatabaseConnection dbc = new DatabaseConnection();
    public Tasklist(){


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

        ListView<Task> list = new ListView<>();
        LinkedList<Task> t = dbc.getTasks();
        dbc.getStatuses(t);
        //ObservableList<Task> listitem;
        //list.setItems(listitems);

        //Scene building
        StackPane root = new StackPane();
        Scene mainScene = new Scene(root, 1280, 720);
        BorderPane rootLayout = new BorderPane();

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
        launch(args);

    }
}
