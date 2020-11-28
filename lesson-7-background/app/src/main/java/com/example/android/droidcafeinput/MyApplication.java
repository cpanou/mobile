package com.example.android.droidcafeinput;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.example.android.droidcafeinput.connectivity.NetworkReceiver;

//  We use the Application class to store state information that should be shared amongst our App Components( Activities/Fragments/Services etc..)
//    - we do not have to worry about instantiating this class since its lifecycle is handled by Android.
//    - we can respond to the applications lifecycle in the same way we do in an activity/fragment/service.
//    - The application class is the first component initialized by the runtime so there shouldn't be any long running processes here, any long process would delay the initialization of our Main Activity
//      and its UI so the application would appear stuck. It should ony be used to initialize shared attributes, register receivers and at best start a service.
//    - we use variables to represent and store our state:
//          1. make sure they are static so that they are shared even amongst other possible instances of this class.
//          2. make sure they are private so that only MyApplication class can change them and expose getters so that other classes can access them.
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // In the onCreate() method we only initialize a networking broadcast receiver
        initializeNetworkReceiver();
    }

    //boolean that indicates if a network is available
    private static boolean networking;
    //public accessor to expose the networking state. This method should be used by our App Components
    public static boolean isNetWorkingAvailable() {
        return networking;
    }
    //A NetworkListener implementation to be passed to the broadcastReceiver so that the Application class
    // can receive network state changes and update the "networking" flag.
    private static final NetworkReceiver.NetworkListener networkListener = new NetworkReceiver.NetworkListener() {
        @Override
        //The onNetworkUpdate() method will be called by the BroadcastReceiver when a change is received by the system.
        public void onNetworkUpdate(boolean networking) {
            MyApplication.networking = networking;
        }
    };

    //The initializeNetworkReceiver() method creates an instance of our Broadcast receiver
    // and passes to it an implementation of the NetworkReceiver.NetworkListener to receive changes in the network state
    private void initializeNetworkReceiver() {
        //To register a Broadcast receiver we need an intent-filter that "filter" a specific action from Android.
        IntentFilter intentFilter = new IntentFilter();
        //The "ConnectivityManager.CONNECTIVITY_ACTION" flag specifies that the intent-filter we created will "filter" connectivity state changes
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        //Finally we need a class that extends Android's BroadCastReceiver see NetworkReceiver.
        //The NetworkReceiver class we created receives a NetworkListener so that it propagates events received by the system.
        NetworkReceiver networkReceiver = new NetworkReceiver(networkListener);

        // Our Broadcast receiver is registered together with the intent-filter. Here is where we specify which actions the receiver will respond to
        //      e.g. if we registered the same networkReceiver with another intent-filter, the receiver would respond to other types of events.
        this.registerReceiver(networkReceiver, intentFilter);

        //Right after we register our receiver, we use its isConnected(Context context) method to set the initial networking state of our application.
        MyApplication.networking = NetworkReceiver.isConnected(this);
        //Here on after the broadcast receiver will automatically update the "networking" flag
    }

}
