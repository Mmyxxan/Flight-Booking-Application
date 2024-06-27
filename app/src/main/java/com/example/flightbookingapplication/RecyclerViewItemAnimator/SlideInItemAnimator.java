package com.example.flightbookingapplication.RecyclerViewItemAnimator;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

public class SlideInItemAnimator extends DefaultItemAnimator {

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        runEnterAnimation(holder.itemView);
        return super.animateAdd(holder);
    }

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        runExitAnimation(holder.itemView);
        return super.animateRemove(holder);
    }

    public void runEnterAnimation(View view) {
        view.setTranslationX(view.getRootView().getWidth());
        view.setAlpha(0);

        view.animate()
                .translationX(0)
                .alpha(1)
                .setDuration(500)
                .start();
    }

    private void runExitAnimation(View view) {
        view.setTranslationX(0);
        view.setAlpha(1);

        view.animate()
                .translationX(view.getRootView().getWidth())
                .alpha(0)
                .setDuration(500)
                .start();
    }
}
