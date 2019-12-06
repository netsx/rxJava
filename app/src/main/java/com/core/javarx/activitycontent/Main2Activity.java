package com.core.javarx.activitycontent;

import android.os.Bundle;

import com.core.javarx.Adaptador;
import com.core.javarx.ApiRetrofit;
import com.core.javarx.RetrofitClient;
import com.core.javarx.pojo.Show;
import com.core.javarx.pojo.Show_;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.core.javarx.R;

import java.util.List;
import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

public class Main2Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText editText;
    RetrofitClient retrofitClient;
    private Disposable subscription = null;

    Adaptador adaptador;
    SwipeRefreshLayout swipeRefreshLayout;
    List<Show_> show_sh ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText =findViewById(R.id.editText3);
        recyclerView = findViewById(R.id.reciclador);


        retrofitClient = new RetrofitClient();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {



                subscription=retrofitClient.provideTwitchService().get( s.toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<List<Show_>>() {
                            @Override
                            public void onNext(final List<Show_> show_s) {
                                Toast.makeText(getApplicationContext(),"tamanio"+show_s.size(),Toast.LENGTH_LONG).show();

                                onREsponseRx(show_s);



                            }

                            @Override
                            public void onError(Throwable e) {


                                Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onComplete() {


                            //    Toast.makeText(getApplicationContext(),"complete",Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });






        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.requestDisallowInterceptTouchEvent(false);
                subscription=retrofitClient.provideTwitchService().get( editText.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<List<Show_>>() {
                            @Override
                            public void onNext(final List<Show_> show_s) {

                             onREsponseRx(show_s);

                            }

                            @Override
                            public void onError(Throwable e) {


                                swipeRefreshLayout.setRefreshing(false);

                                Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onComplete() {


                                swipeRefreshLayout.setRefreshing(false);
                                //    Toast.makeText(getApplicationContext(),"complete",Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });





                    FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void onREsponseRx(List<Show_>s){


//        Toast.makeText(this, s.get(s.size()).getLanguage(),Toast.LENGTH_LONG).show();

        adaptador = new Adaptador(s,this);
        adaptador.notifyDataSetChanged();
     //   adaptador.notifyItemChanged(po);


        recyclerView.setAdapter(adaptador);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        on_refresh(s);


    }

   public void on_refresh(final List<Show_>s){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipeRefreshLayout.setRefreshing(false);
               // adaptador.notifyItemRemoved(s.size()+1);
               // adaptador.notifyItemInserted(s.size()-1);

                s.clear();
                adaptador.notifyDataSetChanged();



                        subscription=retrofitClient.provideTwitchService().get( editText.getText().toString())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new DisposableObserver<List<Show_>>() {
                                    @Override
                                    public void onNext(final List<Show_> show_s) {

                                        onREsponseRx(show_s);

                                    }

                                    @Override
                                    public void onError(Throwable e) {


                                        swipeRefreshLayout.setRefreshing(false);

                                        Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onComplete() {


                                        swipeRefreshLayout.setRefreshing(false);
                                        //    Toast.makeText(getApplicationContext(),"complete",Toast.LENGTH_LONG).show();
                                    }
                                });
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        if (subscription!= null && !subscription.isDisposed()){
            subscription.dispose();
        }
    }
}
