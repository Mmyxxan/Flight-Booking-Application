package com.example.flightbookingapplication.FiltersAdapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingapplication.R;

public class DepartureViewHolder extends RecyclerView.ViewHolder {
    private TextView departureTime;
    public DepartureViewHolder(@NonNull View itemView) {
        super(itemView);
        departureTime = itemView.findViewById(R.id.hour_interval);
    }

    public void changeStyle(boolean selected) {
        if (selected) {
            departureTime.setTextColor(Color.parseColor("#FFFFFF"));
            departureTime.setBackgroundResource(R.drawable.class_background);
        } else {
            departureTime.setTextColor(Color.parseColor("#089083"));
            departureTime.setBackgroundResource(R.drawable.economy_background);
        }
    }

    public void bindView(int position) {
        if (position == 0) {
            departureTime.setText("12AM - 06AM");
        }
        else if (position == 1) {
            departureTime.setText("06AM - 12PM");
            }
        else if (position == 2) {
            departureTime.setText("12PM - 06PM");
        }
        else if (position == 3) {
            departureTime.setText("06PM - 12AM");
        }
    }
}
