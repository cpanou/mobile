package com.example.marvelcomics.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkReceiver extends BroadcastReceiver {

    private NetworkListener listener;
    public NetworkReceiver(NetworkListener listener) {
        this.listener = listener;
    }
    public NetworkReceiver() { }
    public interface NetworkListener {
        void onNetworkUpdate(boolean networking);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (isConnected(context) && listener != null) {
            listener.onNetworkUpdate(true);
            Toast.makeText(context, "Network Available", Toast.LENGTH_LONG).show();
        } else if (listener != null) {
            listener.onNetworkUpdate(false);
            Toast.makeText(context, "Network Disconnected", Toast.LENGTH_LONG).show();
        }
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
            return isNetworkEnabled(connectivityManager, ConnectivityManager.TYPE_WIFI) ||
                    isNetworkEnabled(connectivityManager, ConnectivityManager.TYPE_MOBILE);
        else
            return false;
    }

    private static boolean isNetworkEnabled(ConnectivityManager connectivityManager, int type) {
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(type);
        return networkInfo.isConnected();
    }

}
