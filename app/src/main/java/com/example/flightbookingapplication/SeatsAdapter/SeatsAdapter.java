package com.example.flightbookingapplication.SeatsAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingapplication.FlightModel.FlightSeat;
import com.example.flightbookingapplication.R;

public class SeatsAdapter extends RecyclerView.Adapter<SeatsViewHolder> {
    public interface getUserSeatType {
        int getUserSeatType();
    }
    private getUserSeatType seatTypeListener;
    public void setOnUserSeatTypeListener(getUserSeatType listener) {
        seatTypeListener = listener;
    }
    public void resetData() {
        currentRow = -1;
        currentColumn = -1;
        notifyDataSetChanged();
    }
    public interface onSeatSelectedSuccessful {
        void onSeatSelected(int row, int column);
    }
    private SeatsViewHolder.onSeatSelectedSuccessful selectedListener;
    public void setOnSeatSelectedSuccessful(SeatsViewHolder.onSeatSelectedSuccessful listener) {
        this.selectedListener = listener;
    }
    private int currentRow = -1;
    private int currentColumn = -1;

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public void setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    private FlightSeat[][] seats;

    public SeatsAdapter(FlightSeat[][] seats) {
        this.seats = seats;
    }
    @NonNull
    @Override
    public SeatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seat_row_item, parent, false);
        SeatsViewHolder seatsViewHolder = new SeatsViewHolder(view);
        seatsViewHolder.setShouldDisableRow(new SeatsViewHolder.shouldDisableRow() {
            @Override
            public boolean shouldDisableSeatRow(int row) {
                return ((seatTypeListener.getUserSeatType() == 0) == (row < 2));
            }
        });
        seatsViewHolder.setOnSeatSelectedSuccessful(new SeatsViewHolder.onSeatSelectedSuccessful() {
            @Override
            public void onSeatSelected(int row, int column) {
                currentRow = row;
                currentColumn = column;
                selectedListener.onSeatSelected(row, column);
            }
        });
        return seatsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeatsViewHolder holder, int position) {
        holder.setGetSeatsStatus(new SeatsViewHolder.getSeatsStatus() {
            @Override
            public boolean getSeatStatus(int row, int column) {
                return seats[row][column].isAvailable();
            }
        });
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
