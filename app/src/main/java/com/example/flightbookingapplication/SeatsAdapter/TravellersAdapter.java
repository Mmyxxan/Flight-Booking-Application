package com.example.flightbookingapplication.SeatsAdapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingapplication.R;

public class TravellersAdapter extends RecyclerView.Adapter<TravellersViewHolder> {
    public interface checkCurrentPosition {
        boolean isCurrentPosition(int position);
    }
    private checkCurrentPosition checkCurrentPosition;

    public void setCheckCurrentPosition(checkCurrentPosition checkCurrentPosition) {
        this.checkCurrentPosition = checkCurrentPosition;
    }

    private int num_of_travellers;

    public TravellersAdapter(int num_of_travellers) {
        this.num_of_travellers = num_of_travellers;
    }
    @NonNull
    @Override
    public TravellersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.traveller_item, parent, false);
        TravellersViewHolder holder = new TravellersViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TravellersViewHolder holder, int position) {
        holder.traveller_num.setText(String.valueOf(position + 1));
        if (checkCurrentPosition != null) {
            if (checkCurrentPosition.isCurrentPosition(position)) {
                holder.constraintLayout.setBackgroundResource(R.drawable.search_background);
            }
            else {
                holder.constraintLayout.setBackgroundResource(R.drawable.economy_background);
            }
        }
        holder.itemView.setEnabled(false);
    }

    @Override
    public int getItemCount() {
        return num_of_travellers;
    }
}
