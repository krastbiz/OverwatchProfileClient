package krast.overwatchapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Playtime {

    @SerializedName("quick")
    @Expose
    private String quick;
    @SerializedName("competitive")
    @Expose
    private String competitive;

    public String getQuick() {
        return quick;
    }

    public void setQuick(String quick) {
        this.quick = quick;
    }

    public String getCompetitive() {
        return competitive;
    }

    public void setCompetitive(String competitive) {
        this.competitive = competitive;
    }

}