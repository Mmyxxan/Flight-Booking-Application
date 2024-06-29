package com.example.flightbookingapplication.CustomDialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.flightbookingapplication.R;

public class CustomDialog extends Dialog {

    public CustomDialog(Context context, String searchText) {
        super(context);
        setContentView(R.layout.dialog_search_result);


        TextView dialogText = this.findViewById(R.id.dialog_search_text);
        Button dialogButtonOk = this.findViewById(R.id.dialog_button_ok);

        dialogText.setText(searchText);
        dialogButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        dialogButtonOk.setVisibility(View.GONE);
    }
}
