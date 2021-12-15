package com.example.arbeitsblatt6.logik;

import android.util.Log;

import java.util.ArrayList;

public class Map {
    private Field internMap[];
    private int row, column;

    public Map(int column, int row, int bombs) {
        this.column = column;
        this.row = row;
        initializeMap(bombs);
        setMarkers();
        groupFields();
    }

    private void initializeMap(int bombs) {
        internMap = new Field[column * row];
        int placedBombs = 0;
        while (placedBombs < bombs) {
            for (int y = 0; y < row; y++) {
                for (int x = 0; x < column; x++) {
                    if (internMap[y * row + x] == null)
                        internMap[y * row + x] = new Field(y, x, 0);
                    if (placedBombs < bombs)
                        if (Math.random() < 0.1) {
                            internMap[y * row + x].setValue(9);
                            placedBombs++;
                        }

                }
            }
        }
    }

    private int countBombs(int y, int x) {
        int anzahl = 0;
        int ny;
        int nx;
        for (int i = -1; i < 2; i++) {
            if (y + i >= 0 && y + i < row) {
                for (int j = -1; j < 2; j++) {
                    if (x + j >= 0 && x + j < column) {
                        if (internMap[(y + i) * row + x + j].getValue() == 9) anzahl++;
                    }
                }
            }
        }
        return anzahl;
    }

    private void setMarkers() {
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < column; x++) {
                if (internMap[y * row + x].getValue() != 9)
                    internMap[y * row + x].setValue(countBombs(y, x));
            }
        }
    }

    public ArrayList<Field> getNeighbours(Field cur) {
        ArrayList<Field> neighbours = new ArrayList<>();
        for (int y = -1; y < 2; y++) {
            if (cur.getY() + y >= 0 && cur.getY() + y < row)
                for (int x = -1; x < 2; x++) {
                    if (cur.getX() + x >= 0 && cur.getX() + x < column) {
                        if (!(y == 0 && x == 0)) {
                            neighbours.add(internMap[(cur.getY() + y) * row + cur.getX() + x]);
                        }
                    }
                }
        }
        return neighbours;
    }
    public ArrayList<Field> getNeighbours(int y, int x) {
        return getNeighbours(internMap[y*row + x]);
    }

    private void findUnmarkedFields(Field cur, ArrayList<Field> list) {
//        for (int y = -1; y < 2; y++) {
//            if (cur.getY() + y >= 0 && cur.getY() + y < row)
//                for (int x = -1; x < 2; x++) {
//                    if (cur.getX() + x >= 0 && cur.getX() + x < column) {
//                        if (!(y == 0 && x == 0)) {
//                            Field neighbour = internMap[(cur.getY() + y) * row + cur.getX() + x];
        ArrayList<Field> neighbours = getNeighbours(cur);
        for (Field neighbour : neighbours) {
            if (neighbour.getValue() == 0 && neighbour.getUnmarkedFields() == null) {
                list.add(neighbour);
                neighbour.setUnmarkedFields(list);
                findUnmarkedFields(neighbour, list);
//                if (neighbour.getUnmarkedFields() == null) {
//                    neighbour.setUnmarkedFields(list);
//                    findUnmarkedFields(neighbour, list);
//                }
            }
        }
    }


    private void groupFields() {
        ArrayList<Field> neighbours;
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < column; x++) {
                Field cur = internMap[y * row + x];
                if (cur.getValue() == 0 && cur.getUnmarkedFields() == null) {
                    neighbours = new ArrayList<>();
                    neighbours.add(cur);
                    cur.setUnmarkedFields(neighbours);
                    findUnmarkedFields(cur, neighbours);
                }
            }
        }
    }

    public int checkForBomb(int y, int x) {
        if (internMap[y * row + x].getValue() == 0) return 0;
        if (internMap[y * row + x].getValue() == 9) return 2;
        return 1;
    }

    public String print() {
        String print = new String("    ");
        for (int i = 0; i < column; i++) {
            print +=(i + " ");
        }
        print += "\n";
        int z = 0;
        for (int i = 0; i < internMap.length; i++) {
            if (i % column == 0) print +=("\n" + z++ + "| ");
            print +=(internMap[i].getValue() + " ");
        }
        return print;
    }

    public Field[] getInternMap() {
        return internMap;
    }
}
