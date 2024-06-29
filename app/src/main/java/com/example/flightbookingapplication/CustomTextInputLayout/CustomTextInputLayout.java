package com.example.flightbookingapplication.CustomTextInputLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;

public class CustomTextInputLayout extends TextInputLayout {

    public CustomTextInputLayout(@NonNull Context context) {
        super(context);
    }

    public CustomTextInputLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextInputLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        setEndIconMode(END_ICON_CUSTOM);
        setEndIconOnClickListener(listener);
    }

}
