package com.core.javarx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.core.javarx.activitycontent.Main2Activity;
import com.core.javarx.pojo.Show;
import com.core.javarx.pojo.Show_;

import java.util.BitSet;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity implements BoradCastReciver.ConnectivityReceiverListener{

    Adaptador adaptador;
TextView textView;


    EditText editText;
    EditText editText_;
    Button button;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                /*.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                */

                sessionManager=new SessionManager(this);
                sessionManager.checkLogin();
                editText =findViewById(R.id.editText);
        editText_ =findViewById(R.id.editText2);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sessionManager.createLoginSession(editText.getText().toString(),editText_.getText().toString());

                Intent i = new Intent(getBaseContext(), Main2Activity.class);
                startActivity(i);
            }
        });






          
/*
    apiRetrofit.get(editText.getText().toString()).flatMap(new Function<Show_, ObservableSource<Show>>() {
        @Override
        public ObservableSource<Show> apply(Show_ show_) throws Exception {
            return Observable.fromIterable(show_.getShows());
        }
    }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<Show>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Show show) {

                    System.out.println("RxJava says: " + show.getShow().getName());
                }

                @Override
                public void onError(Throwable e) {

                    System.out.println("RxJava says: " + e.getMessage());

                }

                @Override
                public void onComplete() {

                    System.out.println("RxJava says: Complete");
                }
            });*/

}


    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiStateReceiver, intentFilter);
    }
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(wifiStateReceiver);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener

    }

    @Override
    protected void onPause() {
        super.onPause();

        
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {


        Toast.makeText(this,""+isConnected,Toast.LENGTH_LONG).show();
    }
    private BroadcastReceiver wifiStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                    WifiManager.WIFI_STATE_UNKNOWN);

            switch (wifiStateExtra) {
                case WifiManager.WIFI_STATE_ENABLED:


                    break;
                case WifiManager.WIFI_STATE_DISABLED:


                    break;
            }
        }
    };
}
