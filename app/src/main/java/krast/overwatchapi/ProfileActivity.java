package krast.overwatchapi;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class ProfileActivity extends AppCompatActivity {
    private Example data;

    //user info fields
    public TextView txtUserName;
    public ImageView imageViewAvatar;
    public TextView txtLevel;
    public ImageView txtRank;
    public TextView txtRankValue;
    public TextView txtTotalWins;
    public TextView txtCompetitiveStats;
    public TextView txtCompetitivePlayed;
    public TextView txtQuickPlayed;

    public void initializeComponents(){
        txtUserName = (TextView) findViewById(R.id.txtUserName);
        imageViewAvatar = (ImageView) findViewById(R.id.imgAvatar);
        txtLevel = (TextView) findViewById(R.id.txtLevel);
        txtRank = (ImageView) findViewById(R.id.txtRank);
        txtRankValue = (TextView) findViewById(R.id.txtRankValue);
        txtTotalWins = (TextView) findViewById(R.id.txtTotalWins);
        txtCompetitiveStats = (TextView) findViewById(R.id.txtCompetitiveStats);
        txtCompetitivePlayed = (TextView) findViewById(R.id.txtCompetitivePlayed);
        txtQuickPlayed = (TextView) findViewById(R.id.txtQuickPlayed);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_profile);

        initializeComponents();

        data = DataCore.getInstance().data;
        Example temp = data;

        txtUserName.setText(data.getData().getUsername().toUpperCase());

        String imagePath = data.getData().getAvatar();
        //setting avatar image
        Picasso.with(this)
                .load(imagePath).into(imageViewAvatar);

        //setting level image
        Picasso.with(this)
                .load(data.getData().getLevelFrame()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                txtLevel.setBackground(new BitmapDrawable(getResources(), bitmap));
                txtLevel.setText(data.getData().getLevel().toString());
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Log.d("LEVELFRAME", "FAILED");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.d("LEVELFRAMGE", "Prepare Load");
            }
        });

        //settings skill rating image
        Picasso.with(this)
                .load(data.getData().getCompetitive().getRankImg()).into(txtRank);

        txtRankValue.setText(data.getData().getCompetitive().getRank());

        int totalWins = Integer.parseInt(data.getData().getGames().getQuick().getWins())
                + Integer.parseInt(data.getData().getGames().getCompetitive().getWins());

        txtTotalWins.setText("" + totalWins);

        String competitiveWins = data.getData().getGames().getCompetitive().getWins();
        String competitiveLost = data.getData().getGames().getCompetitive().getLost().toString();
        String competitiveStats = competitiveWins + " - " + competitiveLost;
        Spannable spannable = new SpannableString(competitiveStats);
        spannable.setSpan(new ForegroundColorSpan(Color.GREEN), 0, competitiveWins.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(Color.RED), competitiveWins.length() + 2, competitiveStats.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        txtCompetitiveStats.setText(spannable, TextView.BufferType.SPANNABLE);

        txtCompetitivePlayed.setText(data.getData().getPlaytime().getCompetitive());
        txtQuickPlayed.setText(data.getData().getPlaytime().getQuick());

    }
}
