package com.example.flightbookingapplication.FiltersAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingapplication.R;

public class DepartureAdapter extends RecyclerView.Adapter<DepartureViewHolder>{
    public interface onItemClickListener {
        void onItemClick(int position);
    }
    private onItemClickListener mListener;
    public void setOnItemClickListener(onItemClickListener listener) {
        mListener = listener;
    }
    private int selectedPosition = -1;

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    @NonNull
    @Override
    public DepartureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.departure_filter_item, parent, false);
        return new DepartureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartureViewHolder holder, int position) {
        holder.changeStyle(position == selectedPosition);
        holder.bindView(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyItemChanged(selectedPosition);
                selectedPosition = position;
                notifyItemChanged(selectedPosition);
                if (mListener != null) {
                    mListener.onItemClick(selectedPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
