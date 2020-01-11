package com.odia.alphabet.history;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.odia.alphabet.R;
import com.odia.alphabet.dashboard.AlphabetGridActivity;
import com.odia.alphabet.draw.dimen.DrawDimensions;
import com.odia.alphabet.utils.AlphabetsList;
import com.odia.alphabet.utils.LocalizeHelper;
import com.odia.alphabet.utils.RoundButton;
import com.odia.alphabet.utils.Utils;
import com.willy.ratingbar.RotationRatingBar;

public class HistoryViewActivity extends AppCompatActivity {

    HistoryAlphabetView historyAlphabetView;
    RotationRatingBar rotationRatingBar;
    RoundButton learnBtn,backBtn,homeBtn;

    TextView historyFinalScoreTxt;
    LocalizeHelper localizeHelper =  new LocalizeHelper();
    int aid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_view);

        historyAlphabetView = (HistoryAlphabetView) findViewById(R.id.history_user_drawing_view);
        rotationRatingBar = (RotationRatingBar) findViewById(R.id.history_rating_result_bar);
        learnBtn = (RoundButton) findViewById(R.id.history_play_stop_btn);
        backBtn = (RoundButton) findViewById(R.id.history_back_btn);
        homeBtn = (RoundButton) findViewById(R.id.history_home_btn);
        historyFinalScoreTxt = (TextView) findViewById(R.id.history_final_score_txt);


        aid = getIntent().getIntExtra("aId", 0);
        final int drawId = getIntent().getIntExtra("drawId", 0);
        final float rating = getIntent().getFloatExtra("rating", 0);

        rotationRatingBar.setRating(rating);

        historyAlphabetView.setAlphabetId(aid);
        historyAlphabetView.setDrawId(drawId);
        historyAlphabetView.setStorkeWidth(5);
        historyAlphabetView.setTextSize(AlphabetsList.getAlphabetTextSize(aid));
        historyAlphabetView.setShowChalk(false);
        historyAlphabetView.getDrawHistoryXY();

        learnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historyAlphabetView.setDrawCMD(1);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryViewActivity.this, AlphabetGridActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        historyFinalScoreTxt.setText(localizeHelper.finalScore);
        historyFinalScoreTxt.setTextSize(localizeHelper.finalScoreSize);
        historyFinalScoreTxt.setTypeface(getTypeFace());

        historyAlphabetView.setTextSize(AlphabetsList.getAlphabetTextSize(aid));

        DrawDimensions.setAlphabetViewHeight(historyAlphabetView);
        DrawDimensions.setRoundButttonSize(backBtn, learnBtn, homeBtn);
        Utils.isMaximumResolution(this);

    }

    public Typeface getTypeFace() {
        return Typeface.createFromAsset(getAssets(), "fonts/"+ localizeHelper.fontName+".ttf");
    }
}
