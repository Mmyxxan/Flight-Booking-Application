package com.example.flightbookingapplication.FragmentBookingRecyclerViewAdapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingapplication.R;

public class BookingViewHolder extends RecyclerView.ViewHolder {
    ImageView booking_item;
    public BookingViewHolder(@NonNull View itemView) {
        super(itemView);
        booking_item = itemView.findViewById(R.id.booking_item);
    }
}
