package com.example.flightbookingapplication.FiltersAdapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingapplication.R;

public class SortViewHolder extends RecyclerView.ViewHolder {
    public interface onRadioButtonClickListener {
        void onRadioButtonClicked(int position);
    }
    private onRadioButtonClickListener listener;
    public void setOnRadioButtonClickListener(onRadioButtonClickListener listener) {
        this.listener = listener;
    }
    public String[] options = {"Arrival time", "Departure time", "Price", "Lowest fare", "Duration"};

    private ImageView radio_button;
    private TextView sort_criteria;
    public SortViewHolder(@NonNull View itemView) {
        super(itemView);
        radio_button = itemView.findViewById(R.id.radio_button);
        radio_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRadioButtonClicked(getAdapterPosition());
            }
        });
        sort_criteria = itemView.findViewById(R.id.sort_criteria);
    }

    public void markAsSelected(boolean selected) {
        if (selected) {
            radio_button.setImageResource(R.drawable.radio_button_selected);
        }
        else {
            radio_button.setImageResource(R.drawable.radio_button_unselected);
        }
    }

    public void bindView(int positiion) {
        sort_criteria.setText(options[positiion]);
    }
}
