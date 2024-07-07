package com.example.flightbookingapplication.CustomViewGroupDraggableBar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DraggableCircle extends View {
    public interface getCx1Cx2Listener {
        float getCx1();
        float getCx2();
    }
    private getCx1Cx2Listener listener;
    public void setGetCx1Cx2Listener(getCx1Cx2Listener listener) {
        this.listener = listener;
    }
    int width, height;
    public int getViewWidth() {
        return width;
    }
    public int getViewHeight() {
        return height;
    }
    public interface onDragCircle1Listener {
        void onDragCircle1(float x);
    }
    private onDragCircle1Listener listener1;
    public void setOnDragCircle1Listener(onDragCircle1Listener listener) {
        this.listener1 = listener;
    }
    public interface onDragCircle2Listener {
        void onDragCircle2(float x);
    }
    private onDragCircle2Listener listener2;
    public void setOnDragCircle2Listener(onDragCircle2Listener listener) {
        this.listener2 = listener;
    }
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
        Log.d("DraggableCircle", "w: " + w + ", h: " + h);
        width = w;
        height = h;
//        cx1 = w / 4f;
        cy1 = h / 2f;
//        cx2 = 3 * w / 4f;
        cy2 = h / 2f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(frameColor);
        canvas.drawRect(0, getHeight() / 2f - circleRadius / 8f, getWidth(), getHeight() / 2f + circleRadius / 8f, paint);
        paint.setColor(selectFrameColor);
        if (!isDraggingCircle1 && !isDraggingCircle2) canvas.drawRect(listener.getCx1(), getHeight() / 2f - circleRadius / 8f, listener.getCx2(), getHeight() / 2f + circleRadius / 8f, paint);
        else if (isDraggingCircle1) {
            canvas.drawRect(cx1, getHeight() / 2f - circleRadius / 8f, listener.getCx2(), getHeight() / 2f + circleRadius / 8f, paint);
        }
        else if (isDraggingCircle2) {
            canvas.drawRect(listener.getCx1(), getHeight() / 2f - circleRadius / 8f, cx2, getHeight() / 2f + circleRadius / 8f, paint);
        }
        paint.setColor(circleColor);
        if (!isDraggingCircle1) {
            cx1 = listener.getCx1();
            canvas.drawCircle(listener.getCx1(), cy1, circleRadius, paint);
        }
        else {
            canvas.drawCircle(cx1, cy1, circleRadius, paint);
        }
        if (!isDraggingCircle2) {
            cx2 = listener.getCx2();
            canvas.drawCircle(listener.getCx2(), cy2, circleRadius, paint);
        }
        else {
            canvas.drawCircle(cx2, cy2, circleRadius, paint);
        }
        Log.d("DraggableCircle", "cx1: " + cx1 + ", cy1: " + cy1 + ", cx2: " + cx2 + ", cy2: " + cy2);
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
                    listener1.onDragCircle1(cx1 + dx1);
                    setPosForCircle1(cx1 + dx1, cy1 + dy1);
                    return true;
                } else if (isDraggingCircle2) {
                    float dx2 = x - lastX2;
                    float dy2 = y - lastY2;
                    lastX2 = x;
                    lastY2 = y;
                    listener2.onDragCircle2(cx2 + dx2);
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
//        if (posX > cx2 - 10) return;
        cx1 = posX;
        if (cx1 > width - 10) cx1 = width - 10;
        else if (cx1 < 10) cx1 = 10;
        cy1 = getHeight() / 2f;
        invalidate();
    }

    public void setPosForCircle1(float posX) {
//        if (posX > cx2 - 10) return;
        cx1 = posX;
        if (cx1 > width - 10) cx1 = width - 10;
        else if (cx1 < 10) cx1 = 10;
        invalidate();
    }

    public void setPosForCircle2(float posX, float posY) {
//        if (posX < cx1 + 10) return;
        cx2 = posX;
        if (cx2 > width - 10) cx2 = width - 10;
        else if (cx2 < 10) cx2 = 10;
        cy2 = getHeight() / 2f;
        invalidate();
    }

    public void setPosForCircle2(float posX) {
//        if (posX < cx1 + 10) return;
        cx2 = posX;
        if (cx2 > width - 10) cx2 = width - 10;
        else if (cx2 < 10) cx2 = 10;
        invalidate();
    }
}
