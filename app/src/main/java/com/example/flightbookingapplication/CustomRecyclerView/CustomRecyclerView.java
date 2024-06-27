package com.example.flightbookingapplication.CustomRecyclerView;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingapplication.RecyclerViewItemAnimator.SlideInItemAnimator;

public class CustomRecyclerView extends RecyclerView implements View.OnClickListener {

    int currentPosition;

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;

    }

    public CustomRecyclerView(@NonNull Context context) {
        super(context);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void scrollToPositionWithAnimation(final RecyclerView recyclerView, final int position) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForLayoutPosition(position);
                if (viewHolder != null) {
                    SlideInItemAnimator animator = (SlideInItemAnimator) recyclerView.getItemAnimator();
                    animator.runEnterAnimation(viewHolder.itemView);
                }
            }
        }, 0); // Delay to ensure the item is laid out before animating
        recyclerView.smoothScrollToPosition(position);
    }

    @Override
    public void onClick(View view) {
        scrollToPositionWithAnimation(this, currentPosition + 1);
    }
}
