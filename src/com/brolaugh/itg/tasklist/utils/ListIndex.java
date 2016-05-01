package com.brolaugh.itg.tasklist.utils;

import com.brolaugh.itg.tasklist.graphical.TaskItem;

/**
 * Created by hannes.kindstrommer on 2016-04-29.
 */
public class ListIndex {
    private int task;
    private int index;
    public ListIndex(int task, int index){
        this.task = task;
        this.index = index;
    }

    public int getTask() {
        return task;
    }

    public int getIndex() {
        return index;
    }
}
