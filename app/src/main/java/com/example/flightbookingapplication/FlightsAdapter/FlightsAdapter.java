package com.example.flightbookingapplication.FlightsAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingapplication.FlightModel.Flight;
import com.example.flightbookingapplication.R;

import java.util.List;

public class FlightsAdapter extends RecyclerView.Adapter<FlightsViewHolder> {
    List<Flight> flights;
    private int class_type;
    public interface onFlightClickListener {
        void onFlightClick(Flight flight);
    }

    private onFlightClickListener listener;
    public void setOnFlightClickListener(onFlightClickListener listener) {
        this.listener = listener;
    }

    public FlightsAdapter(int class_type) {
        this.class_type = class_type;
    }
    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
    public List<Flight> getFlights() {
        return flights;
    }

    @NonNull
    @Override
    public FlightsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight_item, parent, false);
        return new FlightsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightsViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onFlightClick(flights.get(position));
            }
        });
        holder.tv_from_city.setText(flights.get(position).getOrigin());
        holder.tv_to_city.setText(flights.get(position).getDestination());
        if (class_type == 1) holder.tv_price.setText(String.valueOf(flights.get(position).getHighestPrice()));
        else holder.tv_price.setText(String.valueOf(flights.get(position).getCheapestPrice()));
        String formattedDate = Flight.getMonthDay(flights.get(position).getDepartureDate());
        holder.tv_date.setText(formattedDate);
        holder.tv_from_code.setText(Flight.abbreviated_city(flights.get(position).getOrigin()));
        holder.tv_to_code.setText(Flight.abbreviated_city(flights.get(position).getDestination()));
        holder.tv_number.setText(flights.get(position).getFlightNumber());
        holder.tv_departure.setText(flights.get(position).getDepartureTime());
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }
}
