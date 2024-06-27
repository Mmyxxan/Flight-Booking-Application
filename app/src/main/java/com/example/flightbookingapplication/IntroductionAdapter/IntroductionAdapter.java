package com.example.flightbookingapplication.IntroductionAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingapplication.CustomRecyclerView.CustomRecyclerView;
import com.example.flightbookingapplication.R;

public class IntroductionAdapter extends RecyclerView.Adapter<IntroductionViewHolder> {
    CustomRecyclerView recyclerView;

    @NonNull
    @Override
    public IntroductionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.introduction_item, parent, false);
        return new IntroductionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IntroductionViewHolder holder, int position) {
        holder.bindView(position);
        if (position != 2) {
            recyclerView.setCurrentPosition(position);
            holder.next_button.setOnClickListener(recyclerView);
        }
    }

    public void setRecyclerView(CustomRecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
