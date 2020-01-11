package com.odia.alphabet.draw;


import android.util.Log;

import com.odia.alphabet.model.AlphabetXY;
import com.odia.alphabet.realm.RealmManager;
import com.odia.alphabet.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Awesome PC on 10-Dec-17.
 */
public class GetTouchPoint {

    String TAG = "GetTouchPoint";

    static {
        System.loadLibrary("OdiaAlphabet");
    }

    AlphabetView alphabetView;
    ArrayList<AlphabetXY> alphabetXies;
    AlphabetXY[] alphaXY;

    public GetTouchPoint(AlphabetView alphabetView, ArrayList<AlphabetXY> alphabetXies) {
        this.alphabetView = alphabetView;
        this.alphabetXies = alphabetXies;
        resetDraw();
    }

    /*public int getPoint(float pointX, float pointY) {

        //readAlphabetXY(alphabetXies);

        *//*Log.d(TAG, "Reset : alphaXY : " + alphaXY + " :: " + getMinIndex() + " > " + getCTouchPoint());
        if (alphaXY == null) {
            alphaXY = new AlphabetXY[alphabetXies.size()];
            for (int i = 0; i < alphabetXies.size(); i++) {
                alphaXY[i] = alphabetXies.get(i);
            }
        }*//*

        return getTouchPoint(alphaXY, (int) pointX, (int) pointY);
    }

    public void setStatus(int index, AlphabetXY alphabetXY) {
        alphaXY[index] = alphabetXY;
    }

    public void reset() {
        alphaXY = null;

    }

    public void setCurrentTouchPoint(int ctouchPoint) {
        setTouchPoint(ctouchPoint);
    }*/

    int minIndex = 0, ctouchPoint = 0;
    int nextIndex = 0;

    public int getPoint(float pointX, float pointY) {

        AlphabetXY alphabetXY1 = alphabetXies.get(0);
        float cXi = (float) alphabetXY1.getX();
        float cYi = (float) alphabetXY1.getY();

        AlphabetXY alphabetXY2 = alphabetXies.get(1);
        float midX1 = (cXi + (float) alphabetXY2.getX()) / 2;
        float midY1 = (cYi + (float) alphabetXY2.getY()) / 2;

        double minD = Math.sqrt(Math.pow(midX1 - pointX, 2) + Math.pow(midY1 - pointY, 2));

        if (!initial())
            minIndex = 0;

        for (int i = 0; i < alphabetXies.size(); i++) {

            AlphabetXY alphabetXY = alphabetXies.get(i);

            if (alphabetXY.getStatus() == 1)
                continue;

            nextIndex = i + 1;

            if (i == alphabetXies.size() - 1) {
                nextIndex = i;
            }

            minIndex = getMinTouchPoint(alphabetXies.get(i), alphabetXies.get(nextIndex), (int) pointX, (int) pointY,i, minD);
        }

        return minIndex;
    }

    public void reset() {
        minIndex = 0;
        ctouchPoint = 0;
        resetDraw();
    }

    public void setCurrentTouchPoint(int ctouchPoint) {
        this.ctouchPoint = ctouchPoint;
        setTouchPoint(ctouchPoint);
    }

    private boolean initial() {
        return getPointStatus(0) == 1;
    }

    public int getPointStatus(int touchPoint) {
        return alphabetXies.get(touchPoint).getStatus();
    }

    public native void setTouchPoint(int touchPoint);

    public native void resetDraw();

    public native void readAlphabetXY(ArrayList<AlphabetXY> alphabetXies);

    public native int getMinIndex();

    public native int getCTouchPoint();

    public native int getMinTouchPoint(AlphabetXY alphabetXY, AlphabetXY alphabetNextXY, int pointX, int pointY, int i,double minD);

    public native int getTouchPoint(AlphabetXY[] alphaXY, int pointX, int pointY);
}
