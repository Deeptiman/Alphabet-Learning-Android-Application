package com.odia.alphabet.realm;

import android.util.Log;

import com.odia.alphabet.model.AlphabetSize;

import io.realm.Realm;

/**
 * Created by Awesome PC on 23-Dec-17.
 */
public class AlphabetSizeTransaction implements Realm.Transaction, Realm.Transaction.OnSuccess, Realm.Transaction.OnError {


    String TAG = "ODIADATABASEHELPER";

    public static int ADD_SIZE = 0;

    int transactionType;
    String screenSize;
    int alphabetTextSize;
    int alphabetId;

    public void execute(int transactionType, String screenSize, int alphabetTextSize, int alphabetId) {

        this.transactionType = transactionType;
        this.screenSize = screenSize;
        this.alphabetTextSize = alphabetTextSize;
        this.alphabetId = alphabetId;
        try {
            RealmManager.getRealm().executeTransactionAsync(this, this, this);
        } catch (Exception e) {
            Log.d(TAG, "AlphabetSizeTransaction : Exception : " + e.toString());
        }
    }

    @Override
    public void onError(Throwable error) {
    }

    @Override
    public void onSuccess() {
    }

    @Override
    public void execute(Realm realm) {

        switch (transactionType) {
            case 0:

                AlphabetSize checkIfExists = realm.where(AlphabetSize.class).equalTo("alphabetId", alphabetId).equalTo("screenSize", screenSize).findFirst();

                if (checkIfExists == null) {
                    AlphabetSize alphabetSize = realm.createObject(AlphabetSize.class);
                    alphabetSize.setAlphabetId(alphabetId);
                    alphabetSize.setScreenSize(screenSize);
                    alphabetSize.setTextSize(alphabetTextSize);
                } else {
                     checkIfExists.setTextSize(alphabetTextSize);
                }
                break;
        }
    }
}
