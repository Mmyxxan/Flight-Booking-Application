package com.example.flightbookingapplication.CustomViewGroupDraggableBar;

import android.util.Log;

public class PositionPriceHelper {
    private static int MAX_PRICE = 500;
    private static int MIN_PRICE = 0;

    public static int PositionToPrice(float position, int width) {
        Log.d("PositionPriceHelper", "PositionToPrice - position: " + position + " width: " + width);
        float fraction = position / (float) width;
        Log.d("PositionPriceHelper", "PositionToPrice - fraction: " + fraction);
        int calculatedPrice = (int) (fraction * (MAX_PRICE - MIN_PRICE));
        Log.d("PositionPriceHelper", "PositionToPrice - calculatedPrice before clamping: " + calculatedPrice);
        int clampedPrice = Math.max(Math.min(calculatedPrice, MAX_PRICE), MIN_PRICE);
        Log.d("PositionPriceHelper", "PositionToPrice - clampedPrice: " + clampedPrice);
        return clampedPrice;
    }

    public static float PriceToPosition(int price, int width) {
        Log.d("PositionPriceHelper", "PriceToPosition - price: " + price + " width: " + width);
        float fraction = (float) price / (MAX_PRICE - MIN_PRICE);
        Log.d("PositionPriceHelper", "PriceToPosition - fraction: " + fraction);
        float position = fraction * width;
        Log.d("PositionPriceHelper", "PriceToPosition - position: " + position);
        return position;
    }
}
