package com.odia.alphabet.realm;

import java.util.List;

/**
 * Created by Awesome PC on 02-May-18.
 */
public class DatabaseHelper {

    int aid;
    int childrenCount = 0, totalChildren = 0;
    String word;
    List<OdiaAlphabetParcel> odiaAlphabetParcels;
    String TAG = "ODIADATABASEHELPER";

    public DatabaseHelper(){
    }

    public void processDB(final int aid, String word,List<OdiaAlphabetParcel> odiaAlphabetParcels,final DatabaseCallback databaseCallback){
        this.aid = aid;
        this.word = word;
        this.odiaAlphabetParcels = odiaAlphabetParcels;

        this.totalChildren = odiaAlphabetParcels.size();
        childrenCount = 0;

        RealmAlphabetXY realmAlphabetXY = new RealmAlphabetXY(aid);
        realmAlphabetXY.execute(RealmAlphabetXY.ADD_XY,odiaAlphabetParcels, new RealmAlphabetXY.RealmXY() {
            @Override
            public void onSuccess() {
                databaseCallback.onDatabaseSuccess();
            }
            @Override
            public void onError() {
                databaseCallback.onDatabaseError();
            }
        });
    }

    public interface DatabaseCallback{
        public void onDatabaseSuccess();
        public void onDatabaseError();
    }

}
