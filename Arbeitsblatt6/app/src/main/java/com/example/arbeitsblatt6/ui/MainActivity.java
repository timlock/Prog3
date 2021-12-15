package com.example.arbeitsblatt6.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arbeitsblatt6.R;
import com.example.arbeitsblatt6.logik.Field;
import com.example.arbeitsblatt6.logik.Map;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Map map;
    private int squareSize, column, row;
    private GridLayout grid;
    private GridLayout fog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeMap( 50, 10, 10, 10);
    }


    public void initializeMap(int squareSize, int row, int column, int bombs) {
        this.squareSize = squareSize;
        this.row = row;
        this.column = column;
        grid = this.findViewById(R.id.Minefield);
        fog = this.findViewById(R.id.Fog);
        map = new Map(row, column, bombs);
        displayMap(row, column);
        drawFog(row, column);
    }

    public void displayMap(int row, int column) {
        TextView field;
        Field[] internMap = map.getInternMap();
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < column; x++) {
                field = new TextView(this);
                field.setWidth(squareSize);
                field.setHeight(squareSize);
                String fieldSymbol = String.valueOf(internMap[y * row + x].getValue());
                if (internMap[y * row + x].getValue() == 9)
                    fieldSymbol = getResources().getString(R.string.bomb);
                field.setText(fieldSymbol);
                field.setGravity(Gravity.CENTER);
                field.setTag("Field" + y + "_" + x);
                grid.addView(field);
            }
        }

    }

    public void drawFog(int row, int column) {
        TextView field;
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < column; x++) {
                field = new TextView(this);
                field.setWidth(squareSize);
                field.setHeight(squareSize);
                field.setGravity(Gravity.CENTER);
                field.setBackgroundColor(getResources().getColor(R.color.brown));
                String tag = new String("Fog" + y + "_" + x);
                field.setTag(tag);
                field.setOnClickListener(this);
                fog.addView(field);
            }
        }
        TextView solution = this.findViewById(R.id.Solution);
        solution.setText(map.print());
    }

    public void liftFog(int clickY, int clickX) {
        ArrayList<Field> fieldsToRemove = map.getInternMap()[clickY*row + clickX].getUnmarkedFields();
        for(Field i: fieldsToRemove){
            ArrayList<Field> neighbours = map.getNeighbours(i.getY(), i.getX());
            for(Field neighbour: neighbours){
                String tag = "Fog";
                tag += neighbour.getY() + "_" + neighbour.getX();
                TextView view =(TextView) fog.findViewWithTag(tag);
                view.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void gameOver() {
        fog.setVisibility(View.INVISIBLE);
    }

    public int[] digestTag(String tag) {
        int[] pos = new int[2];
        int delimiter = tag.indexOf("_");
        pos[0] = Integer.parseInt(tag.substring(3, delimiter));
        pos[1] = Integer.parseInt(tag.substring(delimiter + 1));
        return pos;
    }

    @Override
    public void onClick(View view) {
        String tag = (String) view.getTag();
        int[] pos = digestTag(tag);
        view.setVisibility(View.INVISIBLE);
        switch (map.checkForBomb(pos[0], pos[1])) {
            case 0:
                liftFog(pos[0], pos[1]);
                break;
            case 1:
                break;
            case 2:
                gameOver();
        }
    }
}