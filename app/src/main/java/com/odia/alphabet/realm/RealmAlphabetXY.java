package com.odia.alphabet.realm;

import android.util.Log;

import com.odia.alphabet.model.AlphabetSize;
import com.odia.alphabet.model.AlphabetXY;

import java.util.List;

import io.realm.Realm;

/**
 * Created by deeptiman on 7/12/2017.
 */

public class RealmAlphabetXY implements Realm.Transaction, Realm.Transaction.OnSuccess, Realm.Transaction.OnError {

    String TAG = "ODIADATABASEHELPER";

    public static int ADD_XY = 0;
    int transactionType;
    RealmXY realmXY;

    int aid;
    String screenSize;
    List<OdiaAlphabetParcel> odiaAlphabetParcels;
    OdiaAlphabetCoordinate.CoordinatesBean coordinatesBean;

    public RealmAlphabetXY(int aid) {
        this.aid = aid;
    }

    public void execute(int transactionType, List<OdiaAlphabetParcel> odiaAlphabetParcels,RealmXY realmXY) {
        this.transactionType = transactionType;
        this.odiaAlphabetParcels = odiaAlphabetParcels;
        this.realmXY = realmXY;
        try {
            RealmManager.getRealm().executeTransactionAsync(RealmAlphabetXY.this, RealmAlphabetXY.this, RealmAlphabetXY.this);
        } catch (Exception e) {
            Log.d(TAG, "RealmAlphabetXY : Exception : " + e.toString());
        }
    }

    @Override
    public void execute(Realm realm) {
        switch (transactionType) {
            case 0:

                for(int i=0;i<odiaAlphabetParcels.size();i++){

                    OdiaAlphabetParcel odiaAlphabetParcel = odiaAlphabetParcels.get(i);
                    String screenSize = odiaAlphabetParcel.getScreenSize();
                    int alphabetTextSize = odiaAlphabetParcel.getTextSize();
                    List<OdiaAlphabetCoordinate.CoordinatesBean> coordinatesBeanList = odiaAlphabetParcel.getCoordinatesBeanList();

                    AlphabetSize checkIfExistsAlphabetSize = realm.where(AlphabetSize.class).equalTo("alphabetId", aid).equalTo("screenSize", screenSize).findFirst();

                    if (checkIfExistsAlphabetSize == null) {
                        AlphabetSize alphabetSize = realm.createObject(AlphabetSize.class);
                        alphabetSize.setAlphabetId(aid);
                        alphabetSize.setScreenSize(screenSize);
                        alphabetSize.setTextSize(alphabetTextSize);
                    } else {
                        checkIfExistsAlphabetSize.setTextSize(alphabetTextSize);
                    }

                    for (OdiaAlphabetCoordinate.CoordinatesBean coordinatesBean : coordinatesBeanList){

                        int x = coordinatesBean.getX();
                        int y = coordinatesBean.getY();
                        int xyId = coordinatesBean.getId();
                        int jid = coordinatesBean.getJid();
                        int circularShape = coordinatesBean.getCircularShape();

                        AlphabetXY checkIfExists = realm.where(AlphabetXY.class).equalTo("aId", aid).equalTo("xyId", xyId).equalTo("screenSize", screenSize).findFirst();
                        if (checkIfExists == null) {
                            AlphabetXY alphabetXY = realm.createObject(AlphabetXY.class);
                            alphabetXY.setaId(aid);
                            alphabetXY.setXyId(xyId);
                            alphabetXY.setJid(jid);
                            alphabetXY.setX(x);
                            alphabetXY.setY(y);
                            alphabetXY.setAngle(0);
                            alphabetXY.setScreenSize(screenSize);
                            alphabetXY.setCircularShape(circularShape);
                        } else {
                            checkIfExists.setX(x);
                            checkIfExists.setY(y);
                            checkIfExists.setJid(jid);
                        }
                    }
                }

                break;
        }
    }

    @Override
    public void onError(Throwable error) {
       Log.d(TAG, "RealmAlphabetXY : error >> " + error);
        if (realmXY != null)
            realmXY.onError();
    }

    @Override
    public void onSuccess() {
        Log.d(TAG, "RealmAlphabetXY : onSuccess");
        if (realmXY != null)
            realmXY.onSuccess();
    }

    private int getDBCount() {
        return RealmManager.getRealm().where(AlphabetXY.class).equalTo("aId", aid).equalTo("screenSize", screenSize).findAll().size();
    }

    public interface RealmXY {
        public void onSuccess();

        public void onError();
    }
}
