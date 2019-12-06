package com.core.javarx;

import com.core.javarx.pojo.Query.Show;
import com.core.javarx.pojo.Show_;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRetrofit {


    @GET("search/shows")
    Observable<List<Show_>> get(@Query("q") String page);


}
