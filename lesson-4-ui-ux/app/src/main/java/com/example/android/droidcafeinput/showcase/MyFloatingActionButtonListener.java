package com.example.android.droidcafeinput.showcase;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.example.android.droidcafeinput.CafeActivity;

public class MyFloatingActionButtonListener implements View.OnClickListener {
    private final Context context;
    private String mOrderMessage;

    public MyFloatingActionButtonListener(CafeActivity mainActivity, String mOrderMessage) {
        this.context = mainActivity;
        this.mOrderMessage = mOrderMessage;
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(context);
        myAlertBuilder.setTitle("Make Order?");
        myAlertBuilder.setMessage(mOrderMessage);
        myAlertBuilder.setPositiveButton("OK", getDialogInterfaceListener("Congrats"));
        myAlertBuilder.setNegativeButton("Cancel", getDialogInterfaceListener("Canceled"));
        myAlertBuilder.show();
    }

    private DialogInterface.OnClickListener getDialogInterfaceListener(final String congrats) {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, congrats, Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void setmOrderMessage(String message) {
        this.mOrderMessage = message;
    }

    public String getmOrderMessage() {
        return mOrderMessage;
    }
}
