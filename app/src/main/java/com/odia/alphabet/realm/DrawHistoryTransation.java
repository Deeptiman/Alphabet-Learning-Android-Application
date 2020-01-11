package com.odia.alphabet.realm;

import android.graphics.Bitmap;

import com.odia.alphabet.model.DrawHistory;
import com.odia.alphabet.model.DrawSavePointXY;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by deeptiman on 12/12/2017.
 */

public class DrawHistoryTransation implements Realm.Transaction, Realm.Transaction.OnSuccess, Realm.Transaction.OnError {

    public static int ADD_DRAW_HISTORY = 0;

    int transactionType;
    ArrayList<Float> savePointX;
    ArrayList<Float> savePointY;
    ArrayList<Integer> jointIds;
    float rating;
    int alphaId;
    int drawId;
    Bitmap drawingCache;
    SaveDrawHistoryCallBack saveDrawHistoryCallBack;

    public void execute(int transactionType, ArrayList<Float> savePointX, ArrayList<Float> savePointY, ArrayList<Integer> jointIds, float rating, int alphaId, int drawId, Bitmap drawingCache, SaveDrawHistoryCallBack saveDrawHistoryCallBack) {
        this.transactionType = transactionType;
        this.savePointX = savePointX;
        this.savePointY = savePointY;
        this.jointIds = jointIds;
        this.rating = rating;
        this.alphaId = alphaId;
        this.drawId = drawId;
        this.drawingCache = drawingCache;
        this.saveDrawHistoryCallBack = saveDrawHistoryCallBack;
        try {
            RealmManager.getRealm().executeTransactionAsync(this, this, this);
        } catch (Exception e) {

        }
    }

    @Override
    public void execute(Realm realm) {

        switch (transactionType) {
            case 0:

                DrawHistory checkIfExists = realm.where(DrawHistory.class).equalTo("alphaId", alphaId).equalTo("drawId", drawId).findFirst();

                if (checkIfExists == null) {
                    DrawHistory drawHistory = realm.createObject(DrawHistory.class);
                    drawHistory.setAlphaId(alphaId);
                    drawHistory.setRating(rating);
                    drawHistory.setDrawId(drawId);

                    for (int i = 0; i < savePointX.size(); i++) {
                        DrawSavePointXY drawSavePointXY = realm.createObject(DrawSavePointXY.class);
                        drawSavePointXY.setAlphaId(alphaId);
                        drawSavePointXY.setDrawId(drawId);
                        drawSavePointXY.setJointId(jointIds.get(i));
                        drawSavePointXY.setSavePointX(savePointX.get(i));
                        drawSavePointXY.setSavePointY(savePointY.get(i));
                    }
                }
                break;
        }
    }

    @Override
    public void onError(Throwable error) {
    }

    @Override
    public void onSuccess() {
        saveDrawHistoryCallBack.onSaveSuccess();
    }


    public interface SaveDrawHistoryCallBack {
        public void onSaveSuccess();
    }
}
