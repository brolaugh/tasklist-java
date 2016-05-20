package com.brolaugh.itg.tasklist.utils;

import javafx.scene.control.CheckBox;

public class ListCheckBox extends CheckBox{
    // An extension that makes a checkbox comparable to a task by the statusLevelID which is called dbId in this class.
    private int dbId;
    public ListCheckBox(int dbId){
        this.dbId = dbId;
    }
    public int getDbId(){
        return this.dbId;
    }
}
