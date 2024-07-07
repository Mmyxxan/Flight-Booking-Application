package com.example.flightbookingapplication.FiltersAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingapplication.R;

public class SortAdapter extends RecyclerView.Adapter<SortViewHolder> {
    public interface onItemSelectedListener {
        void onItemSelected(int position);
    }
    private onItemSelectedListener mListener;
    public void setOnItemSelectedListener(onItemSelectedListener listener) {
        mListener = listener;
    }
    private int selectedPosition = -1;
    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    @NonNull
    @Override
    public SortViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sort_item, parent, false);
        SortViewHolder sortViewHolder = new SortViewHolder(view);
        sortViewHolder.setOnRadioButtonClickListener(new SortViewHolder.onRadioButtonClickListener() {
            @Override
            public void onRadioButtonClicked(int position) {
                notifyItemChanged(selectedPosition);
                selectedPosition = position;
                notifyItemChanged(position);
                mListener.onItemSelected(position);
            }
        });
        return sortViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SortViewHolder holder, int position) {
        holder.bindView(position);
        holder.markAsSelected(position == selectedPosition);
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
