package com.odia.alphabet.splashscreen;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.odia.alphabet.R;
import com.odia.alphabet.dashboard.AlphabetGridActivity;
import com.odia.alphabet.draw.dimen.DrawDimensions;
import com.odia.alphabet.realm.OdiaAlphabetMasterTransaction;
import com.odia.alphabet.realm.OdiaAlphabetParcel;
import com.odia.alphabet.security.decrypt.DecryptHelper;
import com.odia.alphabet.security.decrypt.DecryptTask;
import com.odia.alphabet.usecase.UseCase;
import com.odia.alphabet.usecase.UseCaseHandler;
import com.odia.alphabet.utils.AppPreference;
import com.odia.alphabet.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SplashScreen extends AppCompatActivity implements DecryptTask.DecryptTaskCallback {

    ProgressBar progressBar;
    TextView odiaAlphabetTxt, versionTxt, installUpdateTxt;
    AppPreference appPreference;

    Drawable progressDrawable;

    String INSTALL_UPDATE = "INSTALL_UPDATE";
    String TAG = "ODIADATABASEHELPER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        odiaAlphabetTxt = (TextView) findViewById(R.id.odia_alphabet_txt);
        installUpdateTxt = (TextView) findViewById(R.id.installing_update_txt);
        versionTxt = (TextView) findViewById(R.id.version);

        appPreference = new AppPreference(this);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/sarala.ttf");
        odiaAlphabetTxt.setTypeface(myTypeface);
        odiaAlphabetTxt.setText("@");
        odiaAlphabetTxt.setRotation(-5);

        progressDrawable = progressBar.getProgressDrawable().mutate();
        progressDrawable.setColorFilter(Color.parseColor("#FF9800"), android.graphics.PorterDuff.Mode.SRC_IN);
        progressBar.setProgressDrawable(progressDrawable);
        progressBar.setMax(DecryptHelper.totalAlphabet);

        if (!appPreference.getBoolean(INSTALL_UPDATE, false)) {
            installUpdateTxt.setText("Installing update");
            String encryptData = new Utils(getApplicationContext()).loadDataFromAsset();

            UseCaseHandler useCaseHandler = UseCaseHandler.getInstance();
            DecryptTask decryptTask = new DecryptTask(this);

            DecryptTask.RequestValue requestValue = new DecryptTask.RequestValue(encryptData);
            useCaseHandler.execute(decryptTask, requestValue, new UseCase.UseCaseCallback<DecryptTask.ResponseValue, DecryptTask.TaskFinished>() {
                @Override
                public void onSuccess(final DecryptTask.ResponseValue response) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addToDatabase(response.getOdiaAlphabetParcelMap());
                        }
                    });
                }

                @Override
                public void onError() {
                }
            });

        } else {
            installUpdateTxt.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            home(3);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        odiaAlphabetTxt.setTextSize(DrawDimensions.getSplashScreenTextSize());
        versionTxt.setTextSize(DrawDimensions.getVersionTextSize());

        Utils.isMaximumResolution(this);
    }

    private void home(int sec) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, AlphabetGridActivity.class));
                finish();
            }
        }, sec * 1000);
    }

    private void addToDatabase(HashMap<String, HashMap<String, ArrayList<OdiaAlphabetParcel>>> odiaAlphabetParcelMap) {

        final int[] progress = {25};

        Gson gson = new Gson();
        Iterator it = odiaAlphabetParcelMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            String word = (String) pair.getKey();
            HashMap<String, ArrayList<OdiaAlphabetParcel>> odiaAlphabetMap = (HashMap<String, ArrayList<OdiaAlphabetParcel>>) pair.getValue();

            String data = gson.toJson(odiaAlphabetMap);
            OdiaAlphabetMasterTransaction odiaAlphabetMasterTransaction = new OdiaAlphabetMasterTransaction();
            odiaAlphabetMasterTransaction.execute(odiaAlphabetMasterTransaction.ADD_DATA, word, data);

            progress[0]++;

            progressBar.setProgress(progress[0]);

            if (progress[0] == DecryptHelper.totalAlphabet) {
                appPreference.putBoolean(INSTALL_UPDATE, true);
                installUpdateTxt.setText("Finished update successfully");
                home(2);
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    @Override
    public void onPublishProgress(int progress, int total) {
        progressBar.setProgress(progress);
    }
}
