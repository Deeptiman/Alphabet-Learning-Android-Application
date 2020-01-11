package com.odia.alphabet.draw;


import android.util.Log;

import com.odia.alphabet.model.AlphabetXY;

import java.util.ArrayList;

/**
 * Created by Awesome PC on 10-Dec-17.
 */
public class GetTouchPoint {

    static {
        System.loadLibrary("OdiaAlphabet");
    }

    AlphabetView alphabetView;
    ArrayList<AlphabetXY> alphabetXies;

    int minIndex = 0, ctouchPoint = 0;

    public GetTouchPoint(AlphabetView alphabetView, ArrayList<AlphabetXY> alphabetXies) {
        this.alphabetView = alphabetView;
        this.alphabetXies = alphabetXies;
    }

    public int getPoint(float pointX, float pointY) {

        AlphabetXY alphabetXY1 = alphabetXies.get(0);
        float cXi1 = (float) alphabetXY1.getX();
        float cYi1 = (float) alphabetXY1.getY();

        AlphabetXY alphabetXY2 = alphabetXies.get(1);
        float cXi2 = (float) alphabetXY2.getX();
        float cYi2 = (float) alphabetXY2.getY();

        double minD = getminD(pointX, pointY, cXi1, cXi2, cYi1, cYi2);
        double tempD = 0, tempminD = 0;

        if (!initial())
            minIndex = 0;

        int nextIndex = 0, minCount = 0, tempMin = 0, tempCount = 0;

        for (int i = 0; i < alphabetXies.size(); i++) {

            AlphabetXY alphabetXY = alphabetXies.get(i);

            if (alphabetXY.getStatus() == 1)
                continue;

            nextIndex = i + 1;

            if (i == alphabetXies.size() - 1) {
                nextIndex = i;
            }

            float cX = (float) alphabetXY.getX();
            float cY = (float) alphabetXY.getY();

            float nxtX = (float) alphabetXies.get(nextIndex).getX();
            float nxtY = (float) alphabetXies.get(nextIndex).getY();

            double d1 = getD1(cX, nxtX, cY, nxtY, pointX, pointY);
            double d3 = getD3(cX, nxtX, cY, nxtY);

            if (d1 < d3) {
                if (minD > d1) {
                    minD = d1;
                    minIndex = i;
                }
            } else if (tempD > d1 && d3 == 0 && i - ctouchPoint <= 1) {
                minIndex = i;
            }
            tempD = d1;

            if (alphabetXY.getCircularShape() == 1) {

                if (minD == tempminD) {
                    minCount++;
                    tempMin = minIndex;
                    tempCount = minCount;
                } else {
                    if (tempCount > minCount) {
                        minIndex = tempMin;
                    }
                    minCount = 0;
                }
                tempminD = minD;
            }
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
    }

    private boolean initial() {
        return getPointStatus(0) == 1;
    }

    public int getPointStatus(int touchPoint) {
        return alphabetXies.get(touchPoint).getStatus();
    }

    public native int getCircularminIndex(double minD, int minIndex);

    public native int getminIndex(double d1, double d3, int i, int ctouchPoint);

    public native double getminD(float pointX, float pointY, float x1, float x2,
                                 float y1, float y2);

    public native double getD1(float x1, float x2,
                               float y1, float y2, float pointX, float pointY);

    public native double getD3(float x1, float x2,
                               float y1, float y2);

    public native void resetDraw();
}