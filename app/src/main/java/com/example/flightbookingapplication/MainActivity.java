package com.example.flightbookingapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.flightbookingapplication.CustomViewGroupDraggableBar.DraggableCircle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the DraggableCircle view from the layout
        DraggableCircle draggableCircle = findViewById(R.id.draggableCircle);

        // Optional: You can set initial position programmatically if needed
        // draggableCircle.setPos(initialX, initialY);
    }
}
