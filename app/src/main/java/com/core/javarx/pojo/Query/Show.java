
package com.core.javarx.pojo.Query;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Show {

    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("show")
    @Expose
    private Show_ show;

    List <Show_>show_s;

    public List<Show_> getShow_s() {
        return show_s;
    }

    public void setShow_s(List<Show_> show_s) {
        this.show_s = show_s;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Show_ getShow() {
        return show;
    }

    public void setShow(Show_ show) {
        this.show = show;
    }

}
