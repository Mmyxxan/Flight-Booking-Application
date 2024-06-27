package com.example.flightbookingapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.flightbookingapplication.CustomRecyclerView.IntroductionRecyclerView;
import com.example.flightbookingapplication.IntroductionAdapter.IntroductionAdapter;
import com.example.flightbookingapplication.IntroductionAdapter.IntroductionViewHolder;
import com.example.flightbookingapplication.RecyclerViewItemAnimator.SlideInItemAnimator;

public class IntroductionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_introduction);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SnapHelper snapHelper = new LinearSnapHelper();
        IntroductionRecyclerView items = this.findViewById(R.id.introduction_recycler_view);
        IntroductionAdapter adapter = new IntroductionAdapter();
        adapter.setRecyclerView(items);
        items.setAdapter(adapter);
        items.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        items.setItemAnimator(new SlideInItemAnimator());
        snapHelper.attachToRecyclerView(items);
    }
}