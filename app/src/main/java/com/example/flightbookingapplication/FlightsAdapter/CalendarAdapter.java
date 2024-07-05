package com.example.flightbookingapplication.FlightsAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingapplication.FlightModel.FlightData;
import com.example.flightbookingapplication.FlightsAdapter.CalendarViewHolder;
import com.example.flightbookingapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {
    private int currentPosition = -1;
    private int numberOfDays;
    private String startDate; // yyyy-MM-dd format

    public interface OnItemSelectedListener {
        void onItemSelected(int position);
    }
    public OnItemSelectedListener onItemSelectedListener;
    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_item, parent, false);
        CalendarViewHolder calendarViewHolder = new CalendarViewHolder(view);
        return calendarViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        // Calculate the date for the current position
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            calendar.setTime(sdf.parse(startDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DAY_OF_YEAR, position);

        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String dayOfWeek = new SimpleDateFormat("EEEE", Locale.getDefault()).format(calendar.getTime());

        holder.bindView(position, String.valueOf(dayOfMonth), FlightData.getDayAbbreviation(dayOfWeek));

        if (currentPosition == position) {
            holder.calendar_item.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.search_background)); // Selected
        } else {
            holder.calendar_item.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.economy_background)); // White
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemChanged(currentPosition); // Reset previously selected item
                currentPosition = holder.getAdapterPosition(); // Update the selected position
                notifyItemChanged(currentPosition); // Update the newly selected item
                if (onItemSelectedListener != null) {
                    onItemSelectedListener.onItemSelected(currentPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return numberOfDays;
    }
}
