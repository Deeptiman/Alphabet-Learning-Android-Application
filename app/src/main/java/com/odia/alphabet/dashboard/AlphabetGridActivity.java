package com.odia.alphabet.dashboard;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.odia.alphabet.R;
import com.odia.alphabet.application.DrawApp;
import com.odia.alphabet.connectivity.ConnectivityReceiver;
import com.odia.alphabet.draw.DrawActivity;
import com.odia.alphabet.menu.MenuAdapter;
import com.odia.alphabet.model.AlphabetXY;
import com.odia.alphabet.realm.DatabaseHelper;
import com.odia.alphabet.realm.OdiaAlphabetMasterTransaction;
import com.odia.alphabet.realm.OdiaAlphabetParcel;
import com.odia.alphabet.realm.RealmManager;
import com.odia.alphabet.slidinguppanel.SlidingUpPanelLayout;
import com.odia.alphabet.utils.AlphabetsList;
import com.odia.alphabet.utils.LocalizeHelper;
import com.odia.alphabet.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;


public class AlphabetGridActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{

    GridView mMenuGridView;
    RecyclerView recyclerView;
    TextView mHeaderTxt;
    SlidingUpPanelLayout mLayout;
    boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_word_grid);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
        mMenuGridView = (GridView) findViewById(R.id.menu_grid);
        recyclerView = (RecyclerView) findViewById(R.id.alphabet_recycle);
        mHeaderTxt = (TextView) findViewById(R.id.header_txt);

        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

            }
        });
        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

        AlphabetGridAdapter adapter = new AlphabetGridAdapter(this, AlphabetsList.getAlphabetList());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(4, 20, true));

        ViewCompat.setNestedScrollingEnabled(recyclerView, false);


        String[] menus = {"About us", "Facebook", "Settings", "Exit"};
        int[] icons = {R.drawable.about_us, R.drawable.facebook, R.drawable.settings, R.drawable.power_off};

        MenuAdapter menuAdapter = new MenuAdapter(this, menus, icons);
        mMenuGridView.setAdapter(menuAdapter);

        GridLayoutManager manager = new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
    }

    LocalizeHelper localizeHelper = new LocalizeHelper();
    @Override
    protected void onResume() {
        super.onResume();

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/"+ localizeHelper.fontName+".ttf");
        mHeaderTxt.setTypeface(myTypeface);
        mHeaderTxt.setText(localizeHelper.alphabetGridHeader);
        mHeaderTxt.setTextSize(localizeHelper.alphabetGridHeaderSize);
        Utils.isMaximumResolution(this);
        DrawApp.getInstance().setConnectivityListener(this);
        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Utils.exitApp(this);
    }

    int aId = 0;
    String word;
    public void openDrawingView(final int aId,final String word){
        this.aId = aId;
        this.word = word;

        final String TAG = "ODIADATABASEHELPER";

        if(RealmManager.getAllModelList(AlphabetXY.class, "aId", aId).size()==0){

            String json = new OdiaAlphabetMasterTransaction().getData(word);

            Gson gson = new Gson();
            Type listType = new TypeToken<Map<String,ArrayList<OdiaAlphabetParcel>>>() {
            }.getType();

            Map<String,ArrayList<OdiaAlphabetParcel>> map = gson.fromJson(json,listType);

            Utils.showDialog(this,"Loading data");

            DatabaseHelper databaseHelper = new DatabaseHelper();
            databaseHelper.processDB(aId, word, map.get(word), new DatabaseHelper.DatabaseCallback() {
                @Override
                public void onDatabaseSuccess() {
                    Utils.dismissDialog();
                    Intent intent = new Intent(AlphabetGridActivity.this, DrawActivity.class);
                    intent.putExtra("aId",aId);
                    intent.putExtra("word",word);
                    startActivity(intent);
                }

                @Override
                public void onDatabaseError() {
                    Utils.dismissDialog();
                }
            });

        } else {

            Intent intent = new Intent(this, DrawActivity.class);
            intent.putExtra("aId", aId);
            intent.putExtra("word",word);
            startActivity(intent);
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        this.isConnected = isConnected;
    }

}
