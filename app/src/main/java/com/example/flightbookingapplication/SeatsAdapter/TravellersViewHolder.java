package com.example.flightbookingapplication.SeatsAdapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingapplication.R;

public class TravellersViewHolder extends RecyclerView.ViewHolder {
    public ConstraintLayout constraintLayout;
    public TextView traveller_num;
    public TravellersViewHolder(@NonNull View itemView) {
        super(itemView);
        constraintLayout = itemView.findViewById(R.id.constraintLayout);
        traveller_num = itemView.findViewById(R.id.traveller_item);
    }
}
