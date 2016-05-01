package com.brolaugh.itg.tasklist.utils;

import javafx.scene.control.CheckBox;

/**
 * Created by hannes.kindstrommer on 2016-04-26.
 */
public class ListCheckBox extends CheckBox{
    private int dbId;
    public ListCheckBox(int dbId){
        this.dbId = dbId;
    }
    public int getDbId(){
        return this.dbId;
    }
}
