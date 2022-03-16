package com.example.arbeitsblatt6.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.arbeitsblatt6.R;
import com.example.arbeitsblatt6.logik.Field;
import com.example.arbeitsblatt6.logik.Map;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Map map;
    private int squareSize, column, row, bombs;
    private GridLayout grid;
    private GridLayout fog;
    private Button resetButton;
    private boolean flageMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        initializeMap(50, 10, 10, 10);
    }


    public void initializeMap(int squareSize, int row, int column, int bombs) {
        this.squareSize = squareSize;
        this.row = row;
        this.column = column;
        this.bombs = bombs;
        grid = this.findViewById(R.id.Minefield);
        fog = this.findViewById(R.id.Fog);
        resetButton = this.findViewById(R.id.resetButton);
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
                String tag = "Fog" + y + "_" + x;
                field.setTag(tag);
                field.setOnClickListener(this);
                fog.addView(field);
            }
        }
//        TextView solution = this.findViewById(R.id.Solution);
//        solution.setText(map.print());
    }
//    public void drawFog(int row, int column) {
//        ImageView field;
//        for (int y = 0; y < row; y++) {
//            for (int x = 0; x < column; x++) {
//                field = new ImageView(this);
//                GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT,GridLayout.LayoutParams.MATCH_PARENT);
//                field.getLayoutParams().height = squareSize;
//                field.getLayoutParams().width = squareSize;
//                field.setImageResource(R.drawable.tile0);
//                String tag = "Fog" + y + "_" + x;
//                field.setTag(tag);
//                field.setOnClickListener(this);
//                fog.addView(field);
//            }
//        }
//        TextView solution = this.findViewById(R.id.Solution);
//        solution.setText(map.print());
//    }

    public void liftFog(int clickY, int clickX) {
        ArrayList<Field> fieldsToRemove = map.getInternMap()[clickY * row + clickX].getUnmarkedFields();
        for (Field i : fieldsToRemove) {
            ArrayList<Field> neighbours = map.getNeighbours(i.getY(), i.getX());
            for (Field neighbour : neighbours) {
                String tag = "Fog";
                tag += neighbour.getY() + "_" + neighbour.getX();
                TextView view =  fog.findViewWithTag(tag);
                view.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void gameOver() {
        fog.setVisibility(View.INVISIBLE);
        resetButton.setText(R.string.gameOver);
    }

    public boolean victoryCheck(){
        TextView field;
        int hiddenFieldsCounter = 0;
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < column; x++) {
                String tag = "Fog" + y + "_" + x;
                field = fog.findViewWithTag(tag);
                if(field.isShown()) hiddenFieldsCounter++;
            }
        }
        if(hiddenFieldsCounter == bombs) return true;
        return false;
    }

    public void victory(){
        resetButton.setText(R.string.victory);
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
        if (flageMode) placeFlag(view);
        else {
            String tag = (String) view.getTag();
            int[] pos = digestTag(tag);
            view.setVisibility(View.INVISIBLE);
            switch (map.checkForBomb(pos[0], pos[1])) {
                case 0:
                    liftFog(pos[0], pos[1]);
                    break;
                case 1:
                    gameOver();
                    break;
                default:
                    if(victoryCheck()) victory();
            }
        }
    }

    public void reset(View view) {
        map = new Map(row,column, bombs);
        resetMap();
        resetFog();
        resetButton.setText(R.string.reset);
    }

    public void resetMap() {
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < column; x++) {
                String tag = "Field";
                tag += y + "_" + x;
                TextView view = grid.findViewWithTag(tag);
                String newMarker = Integer.toString(map.getInternMap()[y*row + x].getValue());
                view.setText(newMarker);
            }
        }
    }

    public void resetFog() {
        fog.setVisibility(View.VISIBLE);
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < column; x++) {
                String tag = "Fog";
                tag += y + "_" + x;
                TextView view = fog.findViewWithTag(tag);
                view.setVisibility(View.VISIBLE);
                view.setText("");
            }
        }
    }
    public void changeFlagMode(View view){
        flageMode = !flageMode;
        if(flageMode) view.setBackgroundColor(this.getResources().getColor(R.color.teal_700));
        else view.setBackgroundColor(this.getResources().getColor(R.color.purple_200));
    }
    public void placeFlag(View view){
        TextView ground = (TextView) view;
        ground.setText(R.string.flag);
    }
}