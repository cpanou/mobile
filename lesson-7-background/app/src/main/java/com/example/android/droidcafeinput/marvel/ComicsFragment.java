package com.example.android.droidcafeinput.marvel;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.droidcafeinput.R;
import com.example.android.droidcafeinput.marvel.dto.CharactersResponse;
import com.example.android.droidcafeinput.marvel.dto.ComicsResponse;


public class ComicsFragment extends Fragment {
    private final String TAG = ComicsFragment.class.getSimpleName();

    private boolean bound = false;
    private MarvelService marvelService;
    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MarvelService.MarvelBinder marvelBinder = (MarvelService.MarvelBinder) iBinder;
            marvelService = marvelBinder.getService();
            Toast.makeText(getActivity(), "SERVICE BOUND", Toast.LENGTH_SHORT).show();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Toast.makeText(getActivity(), "SERVICE UNBOUND", Toast.LENGTH_SHORT).show();
            bound = false;
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_comics, container, false);
        Log.d(ComicsFragment.class.getSimpleName(), "CREATED");

        //The Connectivity Manager was moved to the onCreate method
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        wifiInfo.isConnected();
        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        mobileInfo.isConnected();
        boolean isNetworkingAvailable = wifiInfo.isConnected() || mobileInfo.isConnected();

        setUpStartButtonForComicList(root, isNetworkingAvailable);

        // Assignment :
        //(1) - Create a new Button and a new TextView bellow the existing Views in the fragment_comics.xml.
        //(2) - Create the setUpComicButton method that accepts the root view and the ConnectivityManager objects.
        //(3) - make a call to the the MarvelService ---> getComic() method and pass in an anonymous implementation
        // of the MarvelService.MarvelCallback
        //(4) - in the Callback implementation you need to put the title of the fetched comic to the textView.
        // (Hint) the returned comic is in MarvelResponse ---> ComicsData ---> results ( see the dtos )
        //        a sample response can be seen in the MarvelService class.
        // if an Error response is returned, show the generic error message.

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(getActivity(), MarvelService.class);
        getActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private void setUpStartButtonForComicList(View root, boolean isNetworkingAvailable) {
        Button button = root.findViewById(R.id.http_button);
        final TextView httpText = root.findViewById(R.id.http_text);

        if (!isNetworkingAvailable) {
            String networkNotAvailable = "Please Connect to a Network";

            httpText.setText(networkNotAvailable);
            button.setEnabled(false);
        } else {
            button.setEnabled(true);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    marvelService.getComics(new MarvelService.MarvelCallback<ComicsResponse>() {
                        @Override
                        public void onResponse(ComicsResponse marvelResponse) {
                            if (marvelResponse == null)
                                httpText.setText(R.string.generic_network_error);
                            else
                                httpText.setText(marvelResponse.getAttributionText());
                        }
                    });
                }
            });
        }
    }

    private void setupCharacterButton(View root, boolean isNetworkingAvailable) {
        Button button = root.findViewById(R.id.http_button);
        final TextView httpText = root.findViewById(R.id.http_text);

        if (!isNetworkingAvailable) {
            String networkNotAvailable = "Please Connect to a Network";

            httpText.setText(networkNotAvailable);
            button.setEnabled(false);
        } else {
            final String id = "123123123";
            button.setEnabled(true);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    marvelService.getCharacter(id, new MarvelService.MarvelCallback<CharactersResponse>() {
                        @Override
                        public void onResponse(CharactersResponse marvelResponse) {
                            if(marvelResponse == null) {
                                httpText.setText(R.string.generic_network_error);
                                return;
                            }
                            String title = marvelResponse.getData().getResults().get(0).getName();
                            String thumbnailUrl = marvelResponse.getData().getResults().get(0).getThumbnail().url();
                            httpText.setText(title);
                        }
                    });

                }
            });
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(ComicsFragment.class.getSimpleName(), "DESTROYED");
    }

}