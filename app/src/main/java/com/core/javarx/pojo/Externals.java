
package com.core.javarx.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Externals {

    @SerializedName("tvrage")
    @Expose
    private Object tvrage;
    @SerializedName("thetvdb")
    @Expose
    private Object thetvdb;
    @SerializedName("imdb")
    @Expose
    private Object imdb;

    public Object getTvrage() {
        return tvrage;
    }

    public void setTvrage(Object tvrage) {
        this.tvrage = tvrage;
    }

    public Object getThetvdb() {
        return thetvdb;
    }

    public void setThetvdb(Object thetvdb) {
        this.thetvdb = thetvdb;
    }

    public Object getImdb() {
        return imdb;
    }

    public void setImdb(Object imdb) {
        this.imdb = imdb;
    }

}
