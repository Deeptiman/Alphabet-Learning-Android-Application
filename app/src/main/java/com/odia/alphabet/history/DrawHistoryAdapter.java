package com.odia.alphabet.history;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.odia.alphabet.R;
import com.odia.alphabet.draw.dimen.DrawDimensions;
import com.odia.alphabet.model.DrawHistory;

import io.realm.RealmResults;

/**
 * Created by deeptiman on 12/12/2017.
 */

public class DrawHistoryAdapter extends RecyclerView.Adapter<DrawHistoryViewHolders> {

    private RealmResults<DrawHistory> drawHistories;
    private Activity activity;

    public DrawHistoryAdapter(Activity activity, RealmResults<DrawHistory> drawHistories) {
        this.drawHistories = drawHistories;
        this.activity = activity;
    }

    @Override
    public DrawHistoryViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.draw_history_adapter, null);
        DrawHistoryViewHolders rcv = new DrawHistoryViewHolders(activity,layoutView,drawHistories);
        return rcv;
    }

    @Override
    public void onBindViewHolder(DrawHistoryViewHolders holder, int position) {

        Typeface myTypeface = Typeface.createFromAsset(activity.getAssets(), "fonts/sarala.ttf");
        holder.drawAlphabetTxt.setTypeface(myTypeface);
        holder.drawAlphabetTxt.setAlphabetId(drawHistories.get(position).getAlphaId());
        holder.drawAlphabetTxt.setDrawId(drawHistories.get(position).getDrawId());
        holder.drawAlphabetTxt.setStorkeWidth(10);
        holder.drawAlphabetTxt.setLiftUp(250);

        int alphaId = drawHistories.get(position).getAlphaId();

        if(alphaId==1 || alphaId==9 || alphaId == 11 || alphaId == 16) {
            holder.drawAlphabetTxt.setShifLeft(150);
        } else {
            holder.drawAlphabetTxt.setShifLeft(50);
        }

        holder.drawAlphabetTxt.setDefaultPathColor(Color.WHITE);
        holder.drawAlphabetTxt.setShowChalk(true);
        holder.drawAlphabetTxt.getDrawHistoryXY();

        holder.rotationRatingBar.setRating(drawHistories.get(position).getRating());
        holder.rotationRatingBar.setClickable(false);
        holder.rotationRatingBar.setScrollable(false);

        DrawDimensions.setMyDrawingHeight(holder.mParent);

    }
    @Override
    public int getItemCount() {
        return this.drawHistories.size();
    }
}
