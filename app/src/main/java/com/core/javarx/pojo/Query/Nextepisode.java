
package com.core.javarx.pojo.Query;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Nextepisode {

    @SerializedName("href")
    @Expose
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
