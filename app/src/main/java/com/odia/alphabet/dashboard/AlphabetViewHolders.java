package com.odia.alphabet.dashboard;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.odia.alphabet.R;
import com.odia.alphabet.realm.RealmManager;
import com.odia.alphabet.utils.AlphabetsList;


/**
 * Created by deeptiman on 6/12/2017.
 */

public class AlphabetViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    Activity activity;
    public TextView alphabetTxt;
    Context context = RealmManager.getContext();
    int aId = 0;
    String word;

    public AlphabetViewHolders(Activity activity,View itemView) {
        super(itemView);
        this.activity = activity;
        itemView.setOnClickListener(this);
        alphabetTxt = (TextView)itemView.findViewById(R.id.alphabet_txt);

    }

    @Override
    public void onClick(View view) {

        aId = getPosition();
        word = AlphabetsList.getAlphabet(aId);

        AlphabetGridActivity alphabetGridActivity = (AlphabetGridActivity) activity;
        alphabetGridActivity.openDrawingView(aId,word);

    }

}
