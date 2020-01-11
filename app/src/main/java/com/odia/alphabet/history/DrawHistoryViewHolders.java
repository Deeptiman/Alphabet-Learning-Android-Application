package com.odia.alphabet.history;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.odia.alphabet.R;
import com.odia.alphabet.model.DrawHistory;
import com.odia.alphabet.realm.RealmManager;
import com.willy.ratingbar.RotationRatingBar;

import io.realm.RealmResults;

/**
 * Created by deeptiman on 6/12/2017.
 */

public class DrawHistoryViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    Activity activity;
    public HistoryAlphabetView drawAlphabetTxt;
    public ImageView drawAlphabetHistory;
    public RelativeLayout mParent;
    public RotationRatingBar rotationRatingBar;
    RealmResults<DrawHistory> drawHistories;

    Context context = RealmManager.getContext();


    public DrawHistoryViewHolders(Activity activity, View itemView, RealmResults<DrawHistory> drawHistories) {
        super(itemView);
        this.activity = activity;
        this.drawHistories = drawHistories;
        itemView.setOnClickListener(this);
        mParent = (RelativeLayout) itemView.findViewById(R.id.parent);
        drawAlphabetTxt = (HistoryAlphabetView) itemView.findViewById(R.id.draw_alphabet_txt);
        //drawAlphabetHistory = (ImageView) itemView.findViewById(R.id.draw_alphabet_history);
        rotationRatingBar = (RotationRatingBar) itemView.findViewById(R.id.rating_history_bar);
    }

    @Override
    public void onClick(View view) {

        int drawId = getPosition() + 1;
        int aid = 0;
        if(activity instanceof DrawHistoryActivity){
            aid = ((DrawHistoryActivity) activity).getAid();
        }

        Intent intent = new Intent(activity, HistoryViewActivity.class);
        intent.putExtra("aId", aid);
        intent.putExtra("drawId",drawId);
        intent.putExtra("rating",drawHistories.get(getPosition()).getRating());
        activity.startActivity(intent);
    }

}
