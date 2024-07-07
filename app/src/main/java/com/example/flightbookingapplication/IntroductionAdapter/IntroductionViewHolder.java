package com.example.flightbookingapplication.IntroductionAdapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingapplication.R;

public class IntroductionViewHolder extends RecyclerView.ViewHolder {
    ImageView introduction_item;


    public IntroductionViewHolder(@NonNull View itemView) {
        super(itemView);
        introduction_item = itemView.findViewById(R.id.introduction_item);

    }

    void bindView(int position) {
        if (position == 0) {

            introduction_item.setImageResource(R.drawable.item1);
            introduction_item.setEnabled(false);
        }
        else if (position == 1) {

            introduction_item.setImageResource(R.drawable.item2);
            introduction_item.setEnabled(false);
        }
        else if (position == 2) {

            introduction_item.setImageResource(R.drawable.item3);
            introduction_item.setEnabled(false);

        }
    }

}
