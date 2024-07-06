package com.example.flightbookingapplication.SeatsAdapter;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class CustomTravellerRecyclerView extends RecyclerView implements TravellersAdapter.checkCurrentPosition {
    private int currentPosition = 0;

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getTravellerTag() {
        return "Traveller " + String.valueOf(currentPosition + 1) + " / Seat ";
    }

    public void setInitialPosition(int position) {
        scrollToPosition(position);
    }

    @Override
    public void scrollToPosition(int position) {
        super.scrollToPosition(position);
        getAdapter().notifyItemChanged(currentPosition);
        currentPosition = position;
        getAdapter().notifyItemChanged(currentPosition);
    }

    public CustomTravellerRecyclerView(@NonNull Context context) {
        super(context);
    }

    public CustomTravellerRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTravellerRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isCurrentPosition(int position) {
        return (position == currentPosition);
    }
}
