package com.example.flightbookingapplication.FlightsAdapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingapplication.R;

public class CalendarViewHolder extends RecyclerView.ViewHolder {
    public TextView day, date;
    public ConstraintLayout calendar_item;

    public CalendarViewHolder(@NonNull View itemView) {
        super(itemView);
        day = itemView.findViewById(R.id.day);
        date = itemView.findViewById(R.id.date);
        calendar_item = itemView.findViewById(R.id.calendar_item);
    }

    public void bindView(int position, String date, String day) {
        this.date.setText(date);
        this.day.setText(day);
    }
}
