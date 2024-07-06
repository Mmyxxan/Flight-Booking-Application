package com.example.flightbookingapplication.SeatsAdapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingapplication.HomeActivity;
import com.example.flightbookingapplication.R;

public class SeatsViewHolder extends RecyclerView.ViewHolder {
    public interface shouldDisableRow {
        boolean shouldDisableSeatRow(int row);
    }
    private shouldDisableRow disableRowListener;
    public void setShouldDisableRow(shouldDisableRow listener) {
        this.disableRowListener = listener;
    }
    public interface onSeatSelectedSuccessful {
        void onSeatSelected(int row, int column);
    }
    private onSeatSelectedSuccessful selectedListener;
    public void setOnSeatSelectedSuccessful(onSeatSelectedSuccessful listener) {
        this.selectedListener = listener;
    }
    public interface getSeatsStatus {
        boolean getSeatStatus(int row, int column);
    }
    private getSeatsStatus seatStatusListener;
    public void setGetSeatsStatus(getSeatsStatus listener) {
        this.seatStatusListener = listener;
    }
    public interface onRowClickListener {
        void onRowClick(int row);
    }
    private onRowClickListener listener;
    public void setOnRowClickListener(onRowClickListener listener) {
        this.listener = listener;
    }
    TextView row_number;
    TextView[] seats;
    public void bindView(int position) {
        row_number.setText(String.valueOf(position + 1));
    }
    public SeatsViewHolder(@NonNull View itemView) {
        super(itemView);
        row_number = itemView.findViewById(R.id.row_number);
        seats = new TextView[4];
        seats[0] = itemView.findViewById(R.id.seat1);
        seats[1] = itemView.findViewById(R.id.seat2);
        seats[2] = itemView.findViewById(R.id.seat3);
        seats[3] = itemView.findViewById(R.id.seat4);
        for (int i = 0; i < 4; i++) {
            int index = i;
            seats[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (disableRowListener.shouldDisableSeatRow(getAdapterPosition())) {
                        new AlertDialog.Builder(view.getContext())
                                .setTitle("Seat type Error!")
                                .setMessage("Please select the seats corresponding to your seat type (or class).")
                                .setPositiveButton(android.R.string.ok, (dialog, which) -> dialog.dismiss())
                                .show();
                        return;
                    }
                    if (listener != null) {
                        listener.onRowClick(getAdapterPosition());
                    }
                    selectedListener.onSeatSelected(getAdapterPosition(), index);
                    resetSeats();
                    seats[index].setBackgroundResource(R.drawable.selected_seat);
                }
            });
        }
    }

    public void resetSeats() {
        for (int i = 0; i < 4; i++) {
            if (seatStatusListener.getSeatStatus(getAdapterPosition(), i)) {
                seats[i].setBackgroundResource(R.drawable.seat);
                seats[i].setEnabled(true);
            }
            else {
                seats[i].setBackgroundResource(R.drawable.booked_seat);
                seats[i].setEnabled(false);
            }
        }
    }
}
