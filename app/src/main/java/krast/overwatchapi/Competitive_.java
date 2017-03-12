package krast.overwatchapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Competitive_ {

    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("rank_img")
    @Expose
    private String rankImg;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRankImg() {
        return rankImg;
    }

    public void setRankImg(String rankImg) {
        this.rankImg = rankImg;
    }

}