package krast.overwatchapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Games {

    @SerializedName("quick")
    @Expose
    private Quick quick;
    @SerializedName("competitive")
    @Expose
    private Competitive competitive;

    public Quick getQuick() {
        return quick;
    }

    public void setQuick(Quick quick) {
        this.quick = quick;
    }

    public Competitive getCompetitive() {
        return competitive;
    }

    public void setCompetitive(Competitive competitive) {
        this.competitive = competitive;
    }

}