package com.brolaugh.itg.tasklist;


import com.brolaugh.itg.tasklist.database.DatabaseConnection;
import com.brolaugh.itg.tasklist.database.StatusLevel;
import com.brolaugh.itg.tasklist.database.Task;
import com.brolaugh.itg.tasklist.graphical.TaskItem;
import com.brolaugh.itg.tasklist.graphical.TaskViewer;
import com.brolaugh.itg.tasklist.utils.ListCheckBox;
import com.brolaugh.itg.tasklist.utils.ListIndex;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private TaskViewer rightMenu;
    private final Menu filterMenu = new Menu("Filter");
    private ListView<TaskItem> list;
    private LinkedList<ListIndex> index = new LinkedList<ListIndex>();

    public Tasklist() {
        tasks = dbc.getTasks();
        dbc.getStatuses(tasks);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //The top menu
        LinkedList<StatusLevel> statusLevels = dbc.getStatusLevels();
        for (StatusLevel sl : statusLevels) {
            ListCheckBox checkbox = new ListCheckBox(sl.getId());
            checkbox.setSelected(true);

            //When value change of the checkbox occurs change is also made to the tasklist
            checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                    if(oldValue != newValue){
                        editViewList(checkbox.getDbId(), newValue);
                    }

                }
            });


            MenuItem menuItemInsert = new MenuItem(sl.getPlainText(), checkbox);
            filterMenu.getItems().add(menuItemInsert);
        }

        final Menu optionsMenu = new Menu("Options");
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(filterMenu, optionsMenu);

        //Loading tasks into the application
        list = new ListView<>();
        ObservableList<TaskItem> listitem = FXCollections.observableArrayList();

        //Adding tasks to the viewable list
        int iterator = 0;
        for (Task task : tasks) {
            listitem.add(new TaskItem(task));
            index.add(new ListIndex(task.getId(), iterator));
        }

        rightMenu = new TaskViewer(tasks.getLast());

        //Catches clicks in list
        list.setOnMouseClicked(event -> {
            rightMenu.changeTask(list.getSelectionModel().getSelectedItem().getTask());
        });

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
        rootLayout.setRight(rightMenu);
        root.getChildren().add(rootLayout);

        //Final touches
        primaryStage.setTitle("Tasklist");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
    private void editViewList(int statusLevelId, Boolean value){

        if(value){
            for(int i = 0; i < index.size(); i++){
                if(statusLevelId == tasks.get(i).getStatuses().getLast().getLevel().getId()){
                    list.getItems().add(index.get(i).getIndex(), new TaskItem(tasks.get(i)));
                }
            }
        }else {

            for (int i = 0; i < list.getItems().size(); i++) {
                if (list.getItems().get(i).getTask().getStatuses().getLast().getLevel().getId() == statusLevelId) {
                    list.getItems().remove(i);
                    i--;
                }

            }
        }
    }

    public static void main(String[] args) {
        new Tasklist();
        launch(args);

    }

}