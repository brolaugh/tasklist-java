package com.brolaugh.itg.tasklist.database;

import java.awt.Color;

public class StatusLevel {
    private int id;
    private String plain_text;
    private String style;
    private Color foregroundColor;
    private Color backgroundColor;

    public StatusLevel(int id, String plain_text, String style){
        this.id = id;
        this.plain_text = plain_text;
        this.style = style;
        if(style.equals("danger")){
            foregroundColor = new Color(254, 241, 240);
            backgroundColor =  new Color(244, 67, 54);
        }
        else if(style.equals("warning")){
            foregroundColor = new Color(255, 236, 230);
            backgroundColor = new Color(255, 87, 34);
        }
        else if(style.equals("info")){

            foregroundColor = new Color(228, 246, 255);
            backgroundColor = new Color(3, 169, 244);
        }
        else if(style.equals("success")){
            foregroundColor = new Color(76, 175, 80);
            backgroundColor = new Color(225, 242, 226);
        }
        else if(style.equals("primary")){
            foregroundColor = new Color(228, 255, 253);
            backgroundColor = new Color(0, 150, 136);
        }
        else if(style.equals("default")){
            foregroundColor = new Color(255,255,255);
            backgroundColor = new Color(238, 238, 238);
        }
        else{
            System.out.println("Faulty style: " + style);
        }
    }
    public boolean equals(StatusLevel s){
        if(id != s.id)
            return false;
        else
            return true;

    }
    public int getId() {
        return id;
    }

    public String getPlainText() {
        return plain_text;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }
    public String getCSSClass(){
        return this.style;
    }
}
