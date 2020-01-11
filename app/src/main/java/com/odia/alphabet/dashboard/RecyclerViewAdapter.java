package com.odia.alphabet.dashboard;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.odia.alphabet.R;
import com.odia.alphabet.model.Alphabet;

import io.realm.RealmResults;

/**
 * Created by deeptiman on 6/12/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private RealmResults<Alphabet> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, RealmResults<Alphabet> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.alphabet_grid_adapter, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/sarala.ttf");
        holder.countryName.setTypeface(myTypeface);
        holder.countryName.setText(itemList.get(position).getAlphabet());
    }
    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
