package com.odia.alphabet.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.odia.alphabet.R;
import com.odia.alphabet.utils.LocalizeHelper;
import com.odia.alphabet.utils.Utils;


public class SettingsActivity extends AppCompatActivity {

    ListView listView;
    LocalizeHelper localizeHelper = new LocalizeHelper();

    String[] labels = {"English (United States)","Odia"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        listView = (ListView) findViewById(R.id.localize_list);

        final LocalizeAdapter localizeAdapter = new LocalizeAdapter(this,labels);
        listView.setAdapter(localizeAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(i==0){
                    localizeHelper.appPreference.putBoolean(LocalizeHelper.LOCALIZE_TXT,false);
                } else {
                    localizeHelper.appPreference.putBoolean(LocalizeHelper.LOCALIZE_TXT,true);
                }

                localizeAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.isMaximumResolution(this);
    }
}
