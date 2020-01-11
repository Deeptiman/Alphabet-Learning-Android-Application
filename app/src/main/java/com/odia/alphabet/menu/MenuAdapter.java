package com.odia.alphabet.menu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.odia.alphabet.R;
import com.odia.alphabet.info.InfoActivity;
import com.odia.alphabet.settings.SettingsActivity;
import com.odia.alphabet.utils.RoundButton;
import com.odia.alphabet.utils.Utils;


/**
 * Created by deeptiman on 7/12/2017.
 */

public class MenuAdapter extends BaseAdapter {

    private final Activity activity;
    private final String[] menus;
    int[] icons;

    // 1
    public MenuAdapter(Activity activity, String[] menus, int[] icons) {
        this.activity = activity;
        this.menus = menus;
        this.icons = icons;
    }

    // 2
    @Override
    public int getCount() {
        return menus.length;
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return menus[position];
    }

    // 5
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(activity);
            convertView = layoutInflater.inflate(R.layout.menu_adapter, null);
        }

        RoundButton button = (RoundButton) convertView.findViewById(R.id.menu_btn);
        button.setCr_icon(icons[position]);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (position){
                    case 0:
                        activity.startActivity(new Intent(activity, InfoActivity.class));
                        break;
                    case 1:
                        String url = "https://www.facebook.com/odiaalphabet/";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        activity.startActivity(i);
                        break;
                    case 2:
                        activity.startActivity(new Intent(activity, SettingsActivity.class));
                        break;
                    case 3:
                        Utils.exitApp(activity);
                        break;
                }
            }
        });

        return convertView;
    }
}
