package com.example.arbeitsblatt6.logik;

public class Map {
    private Field internMap[];
    private int height,width;

    public Map(int width, int height){
        internMap = new Field[width * height];
        this.width = width;
        this.height = height;
    }
}
