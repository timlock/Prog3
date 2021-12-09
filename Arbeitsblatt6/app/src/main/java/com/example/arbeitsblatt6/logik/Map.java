package com.example.arbeitsblatt6.logik;

public class Map {
    private int internMap[];
    private int row, column;

    public Map(int column, int row, int bombs) {
        this.column = column;
        this.row = row;
        initializeMap(bombs);
        setMarkers();
    }

    private void initializeMap(int bombs) {
        internMap = new int[column * row];
        int placedBombs = 0;
        while (placedBombs < bombs) {
            for (int y = 0; y < row; y++) {
                for (int x = 0; x < column; x++) {
                    //internMap[y * row + x] = 0;
                    if (placedBombs < bombs)
                        if (Math.random() < 0.1) {
                            internMap[y * row + x] = 9;
                            placedBombs++;
                        }
                }
            }
        }
    }

    private int getNeighbours(int y, int x) {
        int anzahl = 0;
        int ny;
        int nx;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                nx = x - 1 + i;
                ny = y - 1 + j;
                if (ny >= 0 && ny < row && nx >= 0 && nx < column) {
                    if (internMap[ny * row + nx] == 9) anzahl++;
                }
            }
        }
        return anzahl;
    }

    private void setMarkers() {
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < column; x++) {
                if(internMap[y * row + x] != 9)
                    internMap[y * row + x] = getNeighbours(y, x);
            }
        }
    }

    public boolean checkForBomb(int y, int x) {
        if (internMap[y * row + x] == 9) return true;
        else return false;
    }

    public int[] getInternMap() {
        return internMap;
    }
}
