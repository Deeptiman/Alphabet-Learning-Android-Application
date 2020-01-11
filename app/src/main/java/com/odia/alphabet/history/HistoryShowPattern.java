package com.odia.alphabet.history;

import android.graphics.Path;
import android.graphics.Point;
import android.util.Log;

import com.odia.alphabet.model.DrawSavePointXY;

import io.realm.RealmResults;

/**
 * Created by Awesome PC on 10-Dec-17.
 */
public class HistoryShowPattern {

    HistoryAlphabetView alphabetView;
    RealmResults<DrawSavePointXY> drawSavePointXies;
    int jid = -1;
    int pId = 0;
    Point prevPoint = null;
    Path path = new Path();
    float prevPointX, prevPointY;
    int liftUp = 0;
    int shiftLeft = 0;

    String TAG = "HistoryShowPattern";

    public HistoryShowPattern(HistoryAlphabetView alphabetView, RealmResults<DrawSavePointXY> drawSavePointXies, int liftUp, int shiftLeft) {
        this.alphabetView = alphabetView;
        this.drawSavePointXies = drawSavePointXies;
        this.liftUp = liftUp;
        this.shiftLeft = shiftLeft;
    }

    public Path drawPattern() {
        int index = 0;
        for (DrawSavePointXY drawSavePointXY : drawSavePointXies) {

            float x = drawSavePointXY.getSavePointX() - shiftLeft;
            float y = drawSavePointXY.getSavePointY() - liftUp;
            Point point = new Point((int) x, (int) y);

            if (jid != drawSavePointXY.getJointId()) {
                jid = drawSavePointXY.getJointId();
                pId = 0;
            }
            if (pId == 0) {
                path.moveTo(point.x, point.y);
            } else {

                float midX = (prevPoint.x + point.x) / 2;
                float midY = (prevPoint.y + point.y) / 2;

                int nextDrawId = index + 1;
                if (nextDrawId < drawSavePointXies.size() && jid != drawSavePointXies.get(nextDrawId).getJointId()) {
                    pId = 0;
                }
                if (pId == 1) {
                    path.lineTo(midX, midY);
                } else if (pId > 1) {
                    path.quadTo(prevPoint.x, prevPoint.y, midX, midY);
                }
            }
            pId++;
            index++;
            prevPointX = x;
            prevPointY = y;
            prevPoint = point;
        }
        return path;
    }

}
