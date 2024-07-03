package com.example.flightbookingapplication.CustomViewGroupDraggableBar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DraggableCircle extends View {

    private int circleColor = Color.parseColor("#01635D");
    private int frameColor = Color.parseColor("#B7DFDB");
    private int selectFrameColor = Color.parseColor("#089083");
    private Paint paint;
    private float circleRadius = 25;
    private float cx1, cy1;
    private float cx2, cy2;
    private float lastX1, lastY1;
    private float lastX2, lastY2;
    private boolean isDraggingCircle1 = false;
    private boolean isDraggingCircle2 = false;

    public DraggableCircle(Context context) {
        super(context);
        init();
    }

    public DraggableCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DraggableCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cx1 = w / 4f;
        cy1 = h / 2f;
        cx2 = 3 * w / 4f;
        cy2 = h / 2f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(frameColor);
        canvas.drawRect(0, getHeight() / 2f - circleRadius / 8f, getWidth(), getHeight() / 2f + circleRadius / 8f, paint);
        paint.setColor(selectFrameColor);
        canvas.drawRect(cx1, getHeight() / 2f - circleRadius / 8f, cx2, getHeight() / 2f + circleRadius / 8f, paint);
        paint.setColor(circleColor);
        canvas.drawCircle(cx1, cy1, circleRadius, paint);
        canvas.drawCircle(cx2, cy2, circleRadius, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isInsideCircle1(x, y)) {
                    lastX1 = x;
                    lastY1 = y;
                    isDraggingCircle1 = true;
                    isDraggingCircle2 = false;
                    return true;
                } else if (isInsideCircle2(x, y)) {
                    lastX2 = x;
                    lastY2 = y;
                    isDraggingCircle2 = true;
                    isDraggingCircle1 = false;
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDraggingCircle1) {
                    float dx1 = x - lastX1;
                    float dy1 = y - lastY1;
                    lastX1 = x;
                    lastY1 = y;
                    setPosForCircle1(cx1 + dx1, cy1 + dy1);
                    return true;
                } else if (isDraggingCircle2) {
                    float dx2 = x - lastX2;
                    float dy2 = y - lastY2;
                    lastX2 = x;
                    lastY2 = y;
                    setPosForCircle2(cx2 + dx2, cy2 + dy2);
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                isDraggingCircle1 = false;
                isDraggingCircle2 = false;
                break;
        }
        return super.onTouchEvent(event);
    }

    private boolean isInsideCircle1(float x, float y) {
        float dx = x - cx1;
        float dy = y - cy1;
        return dx * dx + dy * dy <= circleRadius * circleRadius;
    }

    private boolean isInsideCircle2(float x, float y) {
        float dx = x - cx2;
        float dy = y - cy2;
        return dx * dx + dy * dy <= circleRadius * circleRadius;
    }

    public float getPositionX1() {
        return cx1;
    }

    public float getPositionY1() {
        return cy1;
    }

    public float getPositionX2() {
        return cx2;
    }

    public float getPositionY2() {
        return cy2;
    }

    public void setPosForCircle1(float posX, float posY) {
        cx1 = posX;
        cy1 = getHeight() / 2f;
        invalidate();
    }

    public void setPosForCircle2(float posX, float posY) {
        cx2 = posX;
        cy2 = getHeight() / 2f;
        invalidate();
    }
}
