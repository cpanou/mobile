package com.example.android.droidcafeinput.connectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkReceiver extends BroadcastReceiver {

    private NetworkListener listener;
    //The constructor accepts a listener to propagate networking events
    public NetworkReceiver(NetworkListener listener) {
        this.listener = listener;
    }

    public NetworkReceiver() { }

    //public interface to be used by other app components to receive networking updates
    public interface NetworkListener {
        void onNetworkUpdate(boolean networking);
    }

    //The on receive method is invoked by Android with the intent that indicates which action was broadcasted.
    @Override
    public void onReceive(Context context, Intent intent) {
        //The onReceive method checks if there is an available network with isConnected(context) and sends the result to its listener
        if (isConnected(context) && listener != null) {
            listener.onNetworkUpdate(true);
            Toast.makeText(context, "Network Available", Toast.LENGTH_LONG).show();
        } else if (listener != null) {
            listener.onNetworkUpdate(false);
            Toast.makeText(context, "Network Disconnected", Toast.LENGTH_LONG).show();
        }
    }

    //This method checks te connectivity state of the WIFI and MOBILE networks.
    // returns true if there is an available network.
    // returns false if there is no available network and we are offline.
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
