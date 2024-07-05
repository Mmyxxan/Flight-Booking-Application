package com.example.flightbookingapplication.FlightsAdapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingapplication.R;

public class FlightsViewHolder extends RecyclerView.ViewHolder {
    public TextView tv_from_code, tv_from_city;
    public TextView tv_to_code, tv_to_city;
    public TextView tv_date;
    public TextView tv_departure;
    public TextView tv_price;
    public TextView tv_number;

    public FlightsViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_from_code = itemView.findViewById(R.id.tv_from_code);
        tv_from_city = itemView.findViewById(R.id.tv_from_city);
        tv_to_code = itemView.findViewById(R.id.tv_to_code);
        tv_to_city = itemView.findViewById(R.id.tv_to_city);
        tv_date = itemView.findViewById(R.id.tv_date);
        tv_departure = itemView.findViewById(R.id.tv_departure);
        tv_price = itemView.findViewById(R.id.tv_price);
        tv_number = itemView.findViewById(R.id.tv_number);
    }
}
