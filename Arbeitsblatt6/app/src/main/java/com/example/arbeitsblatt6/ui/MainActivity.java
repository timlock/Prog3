package com.example.arbeitsblatt6.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.example.arbeitsblatt6.R;
import com.example.arbeitsblatt6.logik.Map;

public class MainActivity extends AppCompatActivity {
    private Map map;
    private int x, y, width, height, squareSize;
    private GridLayout grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeMap(50,50,100,100,50,10,10,10);
    }


    public void initializeMap(int x, int y, int width, int height, int squareSize, int row, int column, int bombs) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.squareSize = squareSize;
        map = new Map(row, column, bombs );
        displayMap(row, column);
        drawFog(row, column);
        grid = this.findViewById(R.id.Minefield);

    }
    public void displayMap(int row, int column) {
        TextView field;
        int internMap[] = map.getInternMap();
        for (int fy = 0; fy < row; fy++) {
            for (int fx = 0; fx < column; fx++) {
                field = new TextView(this);
                field.setWidth(squareSize);
                field.setHeight(squareSize);
                String fieldSymbol = String.valueOf(internMap[fy * row + fx]);
                if(internMap[fy * row + fx] == 9) fieldSymbol = getResources().getString(R.string.bomb);
                field.setText(fieldSymbol);
                field.setGravity(Gravity.CENTER);
                grid.addView(field);
            }
        }

    }

    public void drawFog(int row, int column) {
        for (int fy = 0; fy < row; fy++) {
            for (int fx = 0; fx < column; fx++) {

            }
        }
    }
}