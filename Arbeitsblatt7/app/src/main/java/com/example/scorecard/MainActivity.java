package com.example.scorecard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private LinearLayout golfersLayout;
    private ScrollView golfersScrollView;
    private ViewGroup viewGroup;
    private Button next;
    private FloatingActionButton golferPlus;
    private Button golferDelete;
    private EditText courseName;
    private EditText holeCount;
    private EditText golferName;
    private ArrayList<Button> golfers = new ArrayList<>();
    private int selected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.main_layout);
        golfersLayout = findViewById(R.id.golferListLayout);
        golfersScrollView = findViewById(R.id.golferListView);
        viewGroup = findViewById(R.id.main_layout);
        next = findViewById(R.id.next);
        golferPlus = findViewById(R.id.golferPlus);
        golferDelete = findViewById(R.id.golferDelete);
        courseName = findViewById(R.id.courseNameInput);
        holeCount = findViewById(R.id.holeCountInput);
        golferName = findViewById(R.id.golferNameInput);
        golfers.add(findViewById(R.id.golfer1));

        golferPlus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Button b = new Button(getApplicationContext());
                b.setText(getString(R.string.player));

                golfersLayout.addView(b);
                golfers.add(b);
                selected = golfers.size() - 1;

                refreshGolferList();
                refreshGolfername();
                refreshGolferListener();
                golfersScrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        golfersScrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });

        golferDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (golfers.size() > selected && selected > -1) {
                    golfersLayout.removeView(golfers.get(selected));
                    golfers.remove(selected);
                    if (selected >= golfers.size()) {
                        selected = golfers.size() - 1;
                    }

                    refreshGolferList();
                    refreshGolfername();
                    refreshGolferListener();
                } else {
                    Log.e("GolferSelection", "Non-existent Golfer is selected.");
                }
            }
        });

        golferName.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(golfers.size() > selected && selected > -1) {
                    golfers.get(selected).setText(s.toString());
                    refreshGolferList();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String sCourseName = courseName.getText().toString();
                String sHoleCount = holeCount.getText().toString();
                int iHoleCount = 0;
                if (sHoleCount.length() > 0) {
                    iHoleCount = Integer.valueOf(sHoleCount);
                }
                String[] sGolfers = new String[golfers.size()];
                for(int i = 0; i < golfers.size(); i++) {
                    sGolfers[i] = golfers.get(i).getText().toString();
                }

                if(sCourseName.length() > 0 && iHoleCount > 0 && sGolfers.length > 0) {
                    Intent intent = new Intent(MainActivity.this, FillInActivity.class);
                    Bundle b = new Bundle();
                    b.putString("courseName", sCourseName);
                    b.putInt("holeCount", iHoleCount);
                    b.putStringArray("golfers", sGolfers);
                    intent.putExtras(b);
                    startActivity(intent);
                    finish();
                } else {
                    String error = getString(R.string.error_start) + " ";
                    if (sCourseName.length() < 1) {
                        error += getString(R.string.course_name) + getString(R.string.name);
                    } else if (iHoleCount < 1) {
                        error += getString(R.string.hole_count);
                    } else if (sGolfers.length < 1) {
                        error += getString(R.string.at_least_one) + getString(R.string.golfer_name);
                    }
                    error += " " + getString(R.string.error_end);
                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                    Log.i("ToastError", error);
                }
            }
        });
    }

    private void refreshGolferListener() {
        for(int i = 0; i < golfers.size(); i++) {
            int finalI = i;
            golfers.get(i).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    selected = finalI;
                    refreshGolfername();
                    refreshGolferList();
                }
            });
        }
    }

    private void refreshGolferList() {
        for(int i = 0; i < golfers.size(); i++) {
            if(selected == i) {
                golfers.get(i).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.purple_500, null));
            } else {
                golfers.get(i).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.gray, null));
            }
            viewGroup.invalidate();
        }
    }

    private void refreshGolfername() {
        if (golfers.size() > selected && selected > -1) {
            golferName.setText(golfers.get(selected).getText());
            viewGroup.invalidate();
        }
    }

}