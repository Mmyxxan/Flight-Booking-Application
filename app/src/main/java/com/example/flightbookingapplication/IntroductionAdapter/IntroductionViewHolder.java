package com.example.flightbookingapplication.IntroductionAdapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbookingapplication.IntroductionActivity;
import com.example.flightbookingapplication.MainActivity;
import com.example.flightbookingapplication.R;
import com.example.flightbookingapplication.WelcomeActivity;

public class IntroductionViewHolder extends RecyclerView.ViewHolder {

    ImageView bar1;
    ImageView bar2;
    ImageView bar3;

    ImageView introduction_item;
    ImageButton next_button;
    public IntroductionViewHolder(@NonNull View itemView) {
        super(itemView);
        bar1 = itemView.findViewById(R.id.bar1);
        bar2 = itemView.findViewById(R.id.bar2);
        bar3 = itemView.findViewById(R.id.bar3);
        introduction_item = itemView.findViewById(R.id.introduction_item);
        next_button = itemView.findViewById(R.id.nextButton);
    }

    void bindView(int position) {
        if (position == 0) {
            bar1.setImageResource(R.drawable.selected_bar);
            bar2.setImageResource(R.drawable.unselected_bar);
            bar3.setImageResource(R.drawable.unselected_bar);
            introduction_item.setImageResource(R.drawable.item1);
        }
        else if (position == 1) {
            bar1.setImageResource(R.drawable.unselected_bar);
            bar2.setImageResource(R.drawable.selected_bar);
            bar3.setImageResource(R.drawable.unselected_bar);
            introduction_item.setImageResource(R.drawable.item2);
        }
        else if (position == 2) {
            bar1.setImageResource(R.drawable.unselected_bar);
            bar2.setImageResource(R.drawable.unselected_bar);
            bar3.setImageResource(R.drawable.selected_bar);
            introduction_item.setImageResource(R.drawable.item3);
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), WelcomeActivity.class);
                    startActivity(view.getContext(), intent, null);
                }
            });
        }
    }

}
