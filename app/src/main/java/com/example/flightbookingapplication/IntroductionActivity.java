package com.example.flightbookingapplication;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.flightbookingapplication.CustomRecyclerView.CustomRecyclerView;
import com.example.flightbookingapplication.CustomRecyclerView.IntroductionRecyclerView;
import com.example.flightbookingapplication.IntroductionAdapter.IntroductionAdapter;
import com.example.flightbookingapplication.IntroductionAdapter.IntroductionViewHolder;
import com.example.flightbookingapplication.RecyclerViewItemAnimator.SlideInItemAnimator;

public class IntroductionActivity extends AppCompatActivity implements CustomRecyclerView.IntroductionListener {

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
        ImageButton next_button = this.findViewById(R.id.nextButton);
        ImageView bar1 = this.findViewById(R.id.bar1);
        ImageView bar2 = this.findViewById(R.id.bar2);
        ImageView bar3 = this.findViewById(R.id.bar3);
        bar1.setImageResource(R.drawable.selected_bar);
        bar2.setImageResource(R.drawable.unselected_bar);
        bar3.setImageResource(R.drawable.unselected_bar);
        SnapHelper snapHelper = new LinearSnapHelper();
        IntroductionRecyclerView items = this.findViewById(R.id.introduction_recycler_view);
        IntroductionAdapter adapter = new IntroductionAdapter();
        adapter.setRecyclerView(items);
        items.setListener(this);
        items.setAdapter(adapter);
        items.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        items.setItemAnimator(new SlideInItemAnimator());
        next_button.setOnClickListener(items);
        snapHelper.attachToRecyclerView(items);
    }

    @Override
    public void onNextClick(int position) {
        if (position == 0) {
            ImageView bar1 = this.findViewById(R.id.bar1);
            bar1.setImageResource(R.drawable.selected_bar);
        }
        else if (position == 1) {
            ImageView bar1 = this.findViewById(R.id.bar1);
            bar1.setImageResource(R.drawable.unselected_bar);
            ImageView bar2 = this.findViewById(R.id.bar2);
            bar2.setImageResource(R.drawable.selected_bar);
        }
        else if (position == 2) {
            ImageView bar1 = this.findViewById(R.id.bar1);
            bar1.setImageResource(R.drawable.unselected_bar);
            ImageView bar2 = this.findViewById(R.id.bar2);
            bar2.setImageResource(R.drawable.unselected_bar);
            ImageView bar3 = this.findViewById(R.id.bar3);
            bar3.setImageResource(R.drawable.selected_bar);
        }
    }
}