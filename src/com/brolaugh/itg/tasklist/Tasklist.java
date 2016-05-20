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
    private LinkedList<Task> tasks;
    private TaskViewer rightMenu;
    private final Menu filterMenu = new Menu("Filter");
    private ListView<TaskItem> list;
    private LinkedList<ListIndex> index = new LinkedList<ListIndex>();

    public Tasklist() {
        //Fetches all the tasks from the database
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

            //Creates the dropdown menu, adds the children to it
            MenuItem menuItemInsert = new MenuItem(sl.getPlainText(), checkbox);
            filterMenu.getItems().add(menuItemInsert);
        }

        final Menu optionsMenu = new Menu("Options");
        //Creates the menubar and adds the elements created above to it
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
        //Creates a TaskViewer and sends the last task with it
        rightMenu = new TaskViewer(tasks.getLast());

        //Whenever an item in the list is selected it becomes the item seen in the rightMenu
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TaskItem>() {
            @Override
            public void changed(ObservableValue<? extends TaskItem> observable, TaskItem oldValue, TaskItem newValue) {
                rightMenu.changeTask(list.getSelectionModel().getSelectedItem().getTask());
            }
        });
        list.setItems(listitem);



        //Scene building
        StackPane root = new StackPane();
        Scene mainScene = new Scene(root, 1280, 600);
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
        /*
        Method is called by the checkboxes in the topmenu change value
        When called it changes the items that are avalible to the viewable list
        if (statusLevel matches) ? add items to list : remove items to list
         */
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
    public static void editWindow(){
        //this.list.getSelectionModel().getSelectedItem().getTask();

    }
    public static void main(String[] args) {
        new Tasklist();
        launch(args);

    }

}