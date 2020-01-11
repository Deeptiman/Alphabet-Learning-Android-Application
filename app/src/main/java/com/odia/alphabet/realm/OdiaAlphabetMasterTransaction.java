package com.odia.alphabet.realm;

import android.util.Log;

import io.realm.Realm;

/**
 * Created by Awesome PC on 03-May-18.
 */
public class OdiaAlphabetMasterTransaction implements Realm.Transaction, Realm.Transaction.OnSuccess, Realm.Transaction.OnError {

    String TAG = "ODIADATABASEHELPER";

    public int ADD_DATA = 0;
    public int GET_DATA = 0;

    int transactionType;
    String word;
    String data;

    public void execute(int transactionType,String word,String data){
        this.transactionType = transactionType;
        this.word = word;
        this.data = data;

        try {
            RealmManager.getRealm().executeTransactionAsync(this, this, this);
        } catch (Exception e) {
            Log.d(TAG,"Exception : OdiaAlphabetMasterTransaction "+e.toString());
        }
    }

    public void execute(int transactionType,String word){
        this.transactionType = transactionType;
        this.word = word;
        try {
            RealmManager.getRealm().executeTransactionAsync(this, this, this);
        } catch (Exception e) {
            Log.d(TAG,"Exception : OdiaAlphabetMasterTransaction "+e.toString());
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

        switch (transactionType){
            case 0:
                OdiaAlphabetMaster ifExists = realm.where(OdiaAlphabetMaster.class).equalTo("word",word).findFirst();
                if(ifExists==null){
                    OdiaAlphabetMaster odiaAlphabetMaster = realm.createObject(OdiaAlphabetMaster.class);
                    odiaAlphabetMaster.setWord(word);
                    odiaAlphabetMaster.setData(data);

                } else {
                    ifExists.setData(data);
                    ifExists.setWord(word);
                }
                break;
        }
    }

    public String getData(String word){
        return RealmManager.getRealm().where(OdiaAlphabetMaster.class).equalTo("word",word).findFirst().getData();
    }

}
