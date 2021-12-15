package com.example.arbeitsblatt6.logik;

import java.util.ArrayList;

public class Field {
    private int y,x,value;
    private ArrayList<Field> unmarkedFields;

    public Field(int y, int x, int value){
        this.y=y;
        this.x=x;
        this.value = value;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public ArrayList<Field> getUnmarkedFields() {
        return unmarkedFields;
    }

    public void setUnmarkedFields(ArrayList<Field> unmarkedFields) {
        this.unmarkedFields = unmarkedFields;
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
