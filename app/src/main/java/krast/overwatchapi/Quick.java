package krast.overwatchapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quick {

    @SerializedName("wins")
    @Expose
    private String wins;

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

}