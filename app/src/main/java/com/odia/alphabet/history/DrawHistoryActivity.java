package com.odia.alphabet.history;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.odia.alphabet.R;
import com.odia.alphabet.dashboard.GridSpacingItemDecoration;
import com.odia.alphabet.model.DrawHistory;
import com.odia.alphabet.realm.RealmManager;
import com.odia.alphabet.utils.LocalizeHelper;
import com.odia.alphabet.utils.Utils;

import io.realm.RealmResults;

public class DrawHistoryActivity extends AppCompatActivity {

    RecyclerView mDrawHistoryList;
    int aid = 0;

    LocalizeHelper localizeHelper = new LocalizeHelper();
    TextView historyMyDrawingTxt;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_history);

        mDrawHistoryList = (RecyclerView) findViewById(R.id.draw_history_list);
        historyMyDrawingTxt = (TextView) findViewById(R.id.history_my_drawing_txt);
        backBtn = (ImageView) findViewById(R.id.back_btn);

        aid = getIntent().getIntExtra("aId", 0);

        RealmResults<DrawHistory> drawHistories = RealmManager.getAllModelListDescending(DrawHistory.class,"alphaId",aid);

        DrawHistoryAdapter adapter = new DrawHistoryAdapter(this, RealmManager.getAllModelListDescending(DrawHistory.class,"alphaId","drawId",aid));
        mDrawHistoryList.setAdapter(adapter);
        mDrawHistoryList.addItemDecoration(new GridSpacingItemDecoration(1,20,true));
        ViewCompat.setNestedScrollingEnabled(mDrawHistoryList, false);
        GridLayoutManager manager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        mDrawHistoryList.setLayoutManager(manager);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        historyMyDrawingTxt.setText(localizeHelper.myDrawings);
        historyMyDrawingTxt.setTextSize(localizeHelper.historyMyDrawingSize);
        historyMyDrawingTxt.setTypeface(getTypeFace());

        Utils.isMaximumResolution(this);
    }

    public Typeface getTypeFace() {
        return Typeface.createFromAsset(getAssets(), "fonts/"+ localizeHelper.fontName+".ttf");
    }

    public int getAid(){
        return aid;
    }

}
