package com.odia.alphabet.dashboard;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.odia.alphabet.R;
import com.odia.alphabet.draw.dimen.DrawDimensions;
import com.odia.alphabet.model.Alphabet;

import io.realm.RealmResults;

/**
 * Created by deeptiman on 6/12/2017.
 */

public class AlphabetGridAdapter extends RecyclerView.Adapter<AlphabetViewHolders> {

    private RealmResults<Alphabet> alphabetList;
    private Activity activity;

    public AlphabetGridAdapter(Activity activity, RealmResults<Alphabet> alphabetList) {
        this.alphabetList = alphabetList;
        this.activity = activity;
    }

    @Override
    public AlphabetViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.alphabet_grid_adapter, null);
        AlphabetViewHolders rcv = new AlphabetViewHolders(activity,layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(AlphabetViewHolders holder, int position) {
        Typeface myTypeface = Typeface.createFromAsset(activity.getAssets(), "fonts/sarala.ttf");
        holder.alphabetTxt.setTypeface(myTypeface);

        String alphabet = alphabetList.get(position).getAlphabet();
        holder.alphabetTxt.setText(alphabet);
        DrawDimensions.setAlphabetGridTextSize(holder.alphabetTxt);
    }
    @Override
    public int getItemCount() {
        return this.alphabetList.size();
    }
}
