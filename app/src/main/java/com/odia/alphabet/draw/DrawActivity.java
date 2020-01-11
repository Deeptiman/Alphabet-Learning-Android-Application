package com.odia.alphabet.draw;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.odia.alphabet.R;
import com.odia.alphabet.draw.dimen.DrawDimensions;
import com.odia.alphabet.history.DrawHistoryActivity;
import com.odia.alphabet.realm.DrawHistoryTransation;
import com.odia.alphabet.learningrate.LearningRate;
import com.odia.alphabet.utils.AnimateView;
import com.odia.alphabet.utils.LocalizeHelper;
import com.odia.alphabet.utils.RoundButton;
import com.odia.alphabet.utils.Utils;
import com.willy.ratingbar.RotationRatingBar;

public class DrawActivity extends AppCompatActivity implements DrawHistoryTransation.SaveDrawHistoryCallBack {

    AlphabetView alphabetView;
    RoundButton learnBtn, writeBtn, historyBtn;
    RoundCornerProgressBar learningRate;

    ImageView homeBtn;

    LinearLayout ratingLayout;
    TextView overallLearningRate;
    RelativeLayout writeLayout, learnLayout, historyLayout;
    View writeSelect, learnSelect, historySelect;
    RotationRatingBar rotationRatingBar;

    TextView writeTxt, learnTxt, myDrawingTxt, finalScoreTxt;

    int LEARN_CMD = 1;
    int WRITE_CMD = 2;
    int LEARNING_STATUS = 0;
    int aid = 0;
    String word;
    LocalizeHelper localizeHelper = new LocalizeHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        aid = getIntent().getIntExtra("aId", 0);
        word = getIntent().getStringExtra("word");

        alphabetView = (AlphabetView) findViewById(R.id.user_drawing_view);
        learningRate = (RoundCornerProgressBar) findViewById(R.id.learning_rate);

        writeBtn = (RoundButton) findViewById(R.id.write_btn);
        learnBtn = (RoundButton) findViewById(R.id.play_stop_btn);
        historyBtn = (RoundButton) findViewById(R.id.result_btn);

        homeBtn = (ImageView) findViewById(R.id.home_btn);

        writeLayout = (RelativeLayout) findViewById(R.id.write_layout);
        learnLayout = (RelativeLayout) findViewById(R.id.learn_layout);
        historyLayout = (RelativeLayout) findViewById(R.id.history_layout);

        ratingLayout = (LinearLayout) findViewById(R.id.final_score_layout);
        rotationRatingBar = (RotationRatingBar) findViewById(R.id.rating_result_bar);


        writeTxt = (TextView) findViewById(R.id.write);
        learnTxt = (TextView) findViewById(R.id.learn);
        myDrawingTxt = (TextView) findViewById(R.id.my_drawing);
        finalScoreTxt = (TextView) findViewById(R.id.final_score_txt);

        overallLearningRate = (TextView) findViewById(R.id.over_learning_rate_txt);
        writeSelect = (View) findViewById(R.id.write_select);
        learnSelect = (View) findViewById(R.id.learn_select);
        historySelect = (View) findViewById(R.id.history_select);

        ratingLayout.setVisibility(View.INVISIBLE);
        learnSelect.setVisibility(View.INVISIBLE);
        historySelect.setVisibility(View.INVISIBLE);
        writeSelect.setVisibility(View.VISIBLE);

        learnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LEARNING_STATUS = 1;
                alphabetView.setDrawCMD(LEARN_CMD);
                learnSelect.setVisibility(View.VISIBLE);
                writeSelect.setVisibility(View.INVISIBLE);
                historySelect.setVisibility(View.INVISIBLE);
                ratingLayout.setVisibility(View.INVISIBLE);
            }
        });

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (LEARNING_STATUS != 1) {
                    alphabetView.setDrawCMD(WRITE_CMD);

                    writeSelect.setVisibility(View.VISIBLE);
                    learnSelect.setVisibility(View.INVISIBLE);
                    historySelect.setVisibility(View.INVISIBLE);
                    ratingLayout.setVisibility(View.INVISIBLE);
                } else {
                    Utils.showToast(DrawActivity.this, "Learning in progress");
                }
            }
        });

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (LEARNING_STATUS != 1) {
                    historySelect.setVisibility(View.VISIBLE);
                    learnSelect.setVisibility(View.INVISIBLE);
                    writeSelect.setVisibility(View.INVISIBLE);
                    ratingLayout.setVisibility(View.INVISIBLE);

                    Intent intent = new Intent(DrawActivity.this, DrawHistoryActivity.class);
                    intent.putExtra("aId", aid);
                    startActivity(intent);

                } else {
                    Utils.showToast(DrawActivity.this, "Learning in progress");
                }
            }
        });


        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getLearningRate();


    }

    Typeface myTypeface;

    @Override
    protected void onResume() {
        super.onResume();

        myTypeface = getTypeFace();

        writeTxt.setText(localizeHelper.write);
        writeTxt.setTypeface(myTypeface);
        writeTxt.setTextSize(localizeHelper.writeSize);

        learnTxt.setText(localizeHelper.learn);
        learnTxt.setTypeface(myTypeface);
        learnTxt.setTextSize(localizeHelper.learnSize);

        myDrawingTxt.setText(localizeHelper.myDrawings);
        myDrawingTxt.setTypeface(myTypeface);
        myDrawingTxt.setTextSize(localizeHelper.myDrawingSize);

        DrawDimensions.setAlphabetViewHeight(alphabetView);
        DrawDimensions.setDrawMenuLayoutWidth(writeLayout, learnLayout, historyLayout);
        DrawDimensions.setDrawMenuTextSize(writeTxt, learnTxt, myDrawingTxt);
        DrawDimensions.setRoundButttonSize(writeBtn, learnBtn, historyBtn);
        DrawDimensions.setOverallTextSize(overallLearningRate);

        Utils.isMaximumResolution(this);

        alphabetView.setWord(word);
        alphabetView.setAlphabetId(aid);
        alphabetView.getAlphabetXY();
        alphabetView.setDrawCMD(WRITE_CMD);
    }

    public void learningEnd() {
        LEARNING_STATUS = 0;
    }

    public void getRating(float rating) {

        AnimateView.animateVisibleView(ratingLayout);

        finalScoreTxt.setText(localizeHelper.finalScore);
        finalScoreTxt.setTextSize(localizeHelper.finalScoreSize);
        finalScoreTxt.setTypeface(getTypeFace());

        if (rating == 0.0)
            rotationRatingBar.setRating(0);

        rotationRatingBar.setRating(rating);

    }

    public DrawHistoryTransation.SaveDrawHistoryCallBack getSaveCallBack() {
        return this;
    }

    public void getLearningRate() {
        int progress = LearningRate.getLearningRate(aid);

        overallLearningRate.setText(localizeHelper.overallRating + " : " + progress + "/10");
        overallLearningRate.setTypeface(getTypeFace());

        learningRate.setProgress(progress);
        if (progress <= 3) {
            learningRate.setProgressColor(getResources().getColor(R.color.custom_progress_red_progress));
        } else if (progress > 3 && progress <= 6) {
            learningRate.setProgressColor(getResources().getColor(R.color.custom_progress_orange_progress));
        } else if (progress > 6) {
            learningRate.setProgressColor(getResources().getColor(R.color.custom_progress_green_progress));
        }
    }

    @Override
    public void onSaveSuccess() {
        getLearningRate();
    }

    public Typeface getTypeFace() {
        return Typeface.createFromAsset(getAssets(), "fonts/" + localizeHelper.fontName + ".ttf");
    }
}
