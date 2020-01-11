package com.odia.alphabet.application;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.odia.alphabet.connectivity.ConnectivityReceiver;
import com.odia.alphabet.realm.RealmManager;
import com.odia.alphabet.utils.AppPreference;
import com.odia.alphabet.utils.LocalizeHelper;


/**
 * Created by deeptiman on 6/11/2017.
 */

public class DrawApp extends MultiDexApplication {


    private static DrawApp mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        RealmManager.initialization(this);
        init();
    }

    private void init() {
        AppPreference appPreference = AppPreference.getAppPreferences(this);
        if(!appPreference.getBoolean("isInstalled", false)){
            appPreference.putBoolean("isInstalled", true);
            appPreference.putBoolean(LocalizeHelper.LOCALIZE_TXT,true);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized DrawApp getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

}
