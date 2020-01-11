package com.odia.alphabet.menu;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.odia.alphabet.R;
import com.odia.alphabet.utils.RoundButton;


/**
 * Created by deeptiman on 14/12/2017.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    Activity activity;
    RoundButton menuBtn;
    public MenuViewHolder(Activity activity, View itemView) {
        super(itemView);
        this.activity = activity;
        menuBtn = (RoundButton) itemView.findViewById(R.id.menu_btn);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
