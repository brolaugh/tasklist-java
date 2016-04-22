package com.brolaugh.itg.tasklist.graphical;

import com.brolaugh.itg.tasklist.database.Status;
import javafx.scene.control.ScrollBar;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by hannes.kindstrommer on 2016-04-12.
 */
public class StatusHistory extends ScrollBar{
    private LinkedList<Status> statuses;
    public StatusHistory(LinkedList<Status> statuses){
        this.statuses = statuses;
        this.setMinHeight(200);

    }
}
