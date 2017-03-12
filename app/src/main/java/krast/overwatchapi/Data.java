package krast.overwatchapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("games")
    @Expose
    private Games games;
    @SerializedName("playtime")
    @Expose
    private Playtime playtime;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("competitive")
    @Expose
    private Competitive_ competitive;
    @SerializedName("levelFrame")
    @Expose
    private String levelFrame;
    @SerializedName("star")
    @Expose
    private String star;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Games getGames() {
        return games;
    }

    public void setGames(Games games) {
        this.games = games;
    }

    public Playtime getPlaytime() {
        return playtime;
    }

    public void setPlaytime(Playtime playtime) {
        this.playtime = playtime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Competitive_ getCompetitive() {
        return competitive;
    }

    public void setCompetitive(Competitive_ competitive) {
        this.competitive = competitive;
    }

    public String getLevelFrame() {
        return levelFrame;
    }

    public void setLevelFrame(String levelFrame) {
        this.levelFrame = levelFrame;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

}