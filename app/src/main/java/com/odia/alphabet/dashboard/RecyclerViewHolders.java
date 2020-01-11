package com.odia.alphabet.dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.odia.alphabet.R;


/**
 * Created by deeptiman on 6/12/2017.
 */

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView countryName;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        countryName = (TextView)itemView.findViewById(R.id.alphabet_txt);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), " Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}
