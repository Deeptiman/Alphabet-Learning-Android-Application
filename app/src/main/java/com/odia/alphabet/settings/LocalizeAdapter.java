package com.odia.alphabet.settings;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.odia.alphabet.R;
import com.odia.alphabet.utils.AppPreference;
import com.odia.alphabet.utils.LocalizeHelper;


/**
 * Created by deeptiman on 14/12/2017.
 */

public class LocalizeAdapter extends BaseAdapter{

    Activity activity;
    String[] labels;
    AppPreference appPreference;
    public LocalizeAdapter(Activity activity,String[] labels){
        this.activity = activity;
        this.labels = labels;
        this.appPreference = AppPreference.getAppPreferences(activity);
    }

    // 2
    @Override
    public int getCount() {
        return labels.length;
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return labels[position];
    }

    // 5
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(activity);
            convertView = layoutInflater.inflate(R.layout.localize_adapter, null);
        }

        TextView labelTxt = (TextView) convertView.findViewById(R.id.label_txt);
        View view = (View) convertView.findViewById(R.id.locale_select);

        labelTxt.setText(labels[position]);

        boolean localize = appPreference.getBoolean(new LocalizeHelper().LOCALIZE_TXT,false);

        view.setVisibility(View.INVISIBLE);

        if(!localize && position==0){
            view.setVisibility(View.VISIBLE);
        } else if(localize && position==1){
            view.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}
