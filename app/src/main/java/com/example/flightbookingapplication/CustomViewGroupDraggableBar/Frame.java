package com.example.flightbookingapplication.CustomViewGroupDraggableBar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Frame extends View {
    private int frameColor = Color.parseColor("#B7DFDB");
    private Paint paint;

    public Frame(Context context) {
        super(context);
        init();
    }

    public Frame(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Frame(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Frame(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(frameColor);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, getHeight() / 2f, getWidth(), getHeight() / 2f, paint);
    }
}
