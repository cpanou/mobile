package com.example.android.droidcafeinput.connectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class InternetConnectionStateChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equalsIgnoreCase("android.net.wifi.WIFI_STATE_CHANGED")) {
            //ConnectivityManager to test for Network Connection
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            wifiInfo.isConnected();
            NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            mobileInfo.isConnected();

            if (wifiInfo.isConnected() || mobileInfo.isConnected()) {
                Toast.makeText(context, "Network Available", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Network Disconnected", Toast.LENGTH_LONG).show();
            }
        }
    }

}
