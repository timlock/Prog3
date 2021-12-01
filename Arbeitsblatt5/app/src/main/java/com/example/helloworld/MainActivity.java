package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Canvas canvas;
    private Bitmap bitmap;
    private Paint paint;
    private int breite = 800;
    private int hoehe = 800;

    private final int textsize = 50;
    private Timer timer = new Timer();

    private int grenzeLinks = 30;
    private int grenzeRechts = 770;
    private int grenzeOben = 400;
    private int grenzeUnten = 770;
    private int ballRadius = 20;
    private float ballX = 100f;
    private float ballY = 700f;
    private float velociteX = 0.3f;
    private float velociteY = 4.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        this.bitmap = Bitmap.createBitmap(this.breite, this.hoehe, Bitmap.Config.ARGB_8888);
        this.canvas = new Canvas(this.bitmap);
        this.imageView = new ImageView(this);
        this.imageView.setImageBitmap(this.bitmap);
        this.paint = new Paint();
        setContentView(imageView);
        this.canvas.drawColor(Color.argb(255, 0, 0, 255));
        this.paint.setTextSize(textsize);
        this.halloWelt();
        this.halloNachbarn();
        this.zeichneSmiley(100);
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                derSpringendePunkt();
            }
        }, 0, 17);
    }

    private void halloWelt() {
        String text = "Hallo Welt!";
        float textWidth = this.paint.measureText(text);
        this.paint.setColor(Color.WHITE);
        this.canvas.drawText(text, breite / 2 - textWidth / 2, 100, this.paint);
    }

    private void textZentrieren(String text, int y) {
        float textWidth = this.paint.measureText(text);
        this.paint.setColor(Color.WHITE);
        this.canvas.drawText(text, breite / 2 - textWidth / 2, y, this.paint);
    }

    private void halloNachbarn() {
        textZentrieren("Hallo Thomas + Frank", 150);
    }

    public void zeichneSmiley(int radius) {
        this.paint.setColor(Color.GREEN);
        this.canvas.drawCircle(breite / 2, hoehe / 2, radius, this.paint);
        this.paint.setColor(Color.BLACK);
        this.canvas.drawRect(breite / 2 - 10, hoehe / 2 - 10, breite / 2 - 5, hoehe / 2 - 5, this.paint);
        this.canvas.drawRect(breite / 2 + 10, hoehe / 2 - 10, breite / 2 + 5, hoehe / 2 - 5, this.paint);
        this.canvas.drawLine(breite / 2 - 30, hoehe / 2 + 20, breite / 2 + 30, hoehe / 2 + 20, this.paint);
    }

    public void derSpringendePunkt() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            Log.i("MainActivity", LocalDateTime.now()
//                    + ": der springende Punkt");
//        }
        this.paint.setColor(Color.BLUE);
        this.canvas.drawCircle(this.ballX, this.ballY, this.ballRadius, this.paint);
        this.ballX += this.velociteX;
        this.ballY += this.velociteY;
        if(this.ballX <= this.grenzeLinks || this.ballX >= this.grenzeRechts) this.velociteX *= -1;
        if(this.ballY <= this.grenzeOben || this.ballY >= this.grenzeUnten) this.velociteY *= -1;
        this.paint.setColor(Color.RED);
        this.canvas.drawCircle(this.ballX, this.ballY, this.ballRadius, this.paint);
        this.imageView.invalidate();

    }

}
