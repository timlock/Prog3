package com.example.scorecard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FillInActivity extends AppCompatActivity {
    private LinearLayout table;
    private int holeCount,playerCount;
    private int fieldWidth = 120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillin);

        Bundle b = getIntent().getExtras();
        String courseName = b.getString("courseName");
        holeCount = b.getInt("holeCount");
        String[] golfers = b.getStringArray("golfers");

        Log.i("courseName", courseName);
        Log.i("holeCount", String.valueOf(holeCount));
        playerCount = golfers.length;
        for(int i = 0; i < golfers.length; i++) {
            Log.i("Golfer " + i, golfers[i]);
        }
        table = this.findViewById(R.id.Table);
        displayTable(courseName, golfers);
    }

    private void displayTable(String coursename, String[] golfers){
        getSupportActionBar().setTitle(coursename);
        createHeader(golfers);
        createColumn();
    }
    public void createHeader(String[] golfers){
        LinearLayout header = new LinearLayout(this);
        header.setOrientation(LinearLayout.HORIZONTAL);
        header.setTag("Header");
        TextView hole = new TextView(this);
        hole.setTag("hole");
        hole.setText(R.string.hole);
        hole.setWidth(fieldWidth);
        header.addView(hole);
        for(int i = 0; i < golfers.length; i++){
            TextView name = new TextView(this);
            name.setTag("Name" + i);
            name.setText(golfers[i]);
            name.setWidth(fieldWidth);
            header.addView(name);
        }
        table.addView(header);
    }
    public void createColumn(){
        LinearLayout column;
        TextView hole;
        for(int i = 0; i < holeCount; i++){
            column = new LinearLayout(this);
            column.setOrientation(LinearLayout.HORIZONTAL);
            column.setTag("Column" + i);
            hole = new TextView(this);
            hole.setTag("hole" + i);
            String nr = String.valueOf(i + 1);
            hole.setText(nr);
            hole.setWidth(fieldWidth);
            column.addView(hole);
            EditText inputField;
            for(int j = 0; j < playerCount; j++){
                inputField = new EditText(this);
                String tag = "Field" + i + "_" + j;
                inputField.setTag(tag);
                inputField.setWidth(fieldWidth);
                column.addView(inputField);
            }
            table.addView(column);
        }
    }

    private int[] getResults() throws Exception {
        int[] results = new int[holeCount* playerCount];
        EditText currentField;
        for(int col = 0; col < holeCount; col++){
            for(int row = 0; row < playerCount; row++){
                String tag = "Field";
                tag += col + "_" + row;
                currentField = table.findViewWithTag(tag);
                String fieldInput = currentField.getText().toString();
                if(!digitCheck(fieldInput)) throw new Exception(tag);
                else results[col * playerCount + row] = Integer.parseInt(fieldInput);
            }
        }
        return results;
    }
    /*
    Überprüft ob der String andere Symbole außer Zahlen enthält
     */
    public boolean digitCheck(String input){
        if(input.isEmpty()) return false;
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        return m.matches();
    }

    public void resultPage(View view){
        int[] results;
        try{
            results = getResults();
        } catch (Exception e){
            String eHole = e.getMessage().substring(5,e.getMessage().indexOf("_"));
            eHole = String.valueOf(Integer.parseInt(eHole) + 1);
            String ePlayer = e.getMessage().substring(e.getMessage().indexOf("_") + 1);
            ePlayer = String.valueOf(Integer.parseInt(ePlayer) + 1);
            String error = this.getString(R.string.wrongInput1);
            error += ePlayer + this.getString(R.string.wrongInput2) + eHole;
            Toast errorMessage = Toast.makeText(FillInActivity.this,error, Toast.LENGTH_LONG);
            errorMessage.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL,0,0);
            errorMessage.show();
            return;
        }
        String courseName = getIntent().getExtras().getString("courseName");
        String[] golfers = getIntent().getExtras().getStringArray("golfers");

        Intent intent = new Intent(FillInActivity.this, ResultActivity.class);
        Bundle b = new Bundle();
        b.putString("courseName", courseName);
        b.putInt("holeCount", holeCount);
        b.putStringArray("golfers", golfers);
        b.putIntArray("results", results);
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }

}