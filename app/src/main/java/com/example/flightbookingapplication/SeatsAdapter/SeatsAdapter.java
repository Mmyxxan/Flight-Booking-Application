package com.example.flightbookingapplication.SeatsAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingapplication.R;

public class SeatsAdapter extends RecyclerView.Adapter<SeatsViewHolder> {
    private int currentRow = -1;
    @NonNull
    @Override
    public SeatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seat_row_item, parent, false);
        SeatsViewHolder seatsViewHolder = new SeatsViewHolder(view);
        return seatsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeatsViewHolder holder, int position) {
        holder.bindView(position);
        if (currentRow != position) {
            holder.resetSeats();
        }
        holder.setOnRowClickListener(new SeatsViewHolder.onRowClickListener() {
            @Override
            public void onRowClick(int row) {
                if (row != currentRow) {
                    notifyItemChanged(currentRow);
                    currentRow = row;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
