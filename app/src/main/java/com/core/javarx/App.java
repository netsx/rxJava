package com.core.javarx;

import android.app.Application;

public class App extends Application {

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized App getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(BoradCastReciver.ConnectivityReceiverListener listener ) {
        BoradCastReciver.connectivityReceiverListener = listener;
    }
}
