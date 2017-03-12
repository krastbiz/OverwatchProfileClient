package krast.overwatchapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Competitive {

    @SerializedName("wins")
    @Expose
    private String wins;
    @SerializedName("lost")
    @Expose
    private Integer lost;
    @SerializedName("played")
    @Expose
    private String played;

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public Integer getLost() {
        return lost;
    }

    public void setLost(Integer lost) {
        this.lost = lost;
    }

    public String getPlayed() {
        return played;
    }

    public void setPlayed(String played) {
        this.played = played;
    }

}