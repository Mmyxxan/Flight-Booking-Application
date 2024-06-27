package com.example.flightbookingapplication;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find the ImageView
        ImageView imageView = this.findViewById(R.id.vector);

        // Create a Path
        Path path = new Path();
        int startX = 49;
        int startY = 2117;
        int endX = 980;
        int endY = 2313;
        RectF oval = new RectF(startX, startY, endX, endY); // Define the bounds of the oval (or circle)
        path.addArc(oval, 180, 180); // Define the arc within the oval. Here, it's from 180 to 0 degrees.

        // Create and start the ObjectAnimator
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, View.X, View.Y, path);
        animator.setDuration(2500); // Set the duration of the animation
        animator.start(); // Start the animation

        // Start your main activity after a delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, IntroductionActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2500); // Duration of splash screen

    }
}