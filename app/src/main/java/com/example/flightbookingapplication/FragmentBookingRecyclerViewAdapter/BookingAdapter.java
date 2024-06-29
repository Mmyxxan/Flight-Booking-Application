package com.example.flightbookingapplication.FragmentBookingRecyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingapplication.R;

public class BookingAdapter extends RecyclerView.Adapter<BookingViewHolder> {
    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        if (position == 0) {
            holder.booking_item.setImageResource(R.drawable.card);
        }
        else if (position == 1) {
            holder.booking_item.setImageResource(R.drawable.card1);
        }
        else if (position == 2) {
            holder.booking_item.setImageResource(R.drawable.card2);
        }
        else if (position == 3) {
            holder.booking_item.setImageResource(R.drawable.card3);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
