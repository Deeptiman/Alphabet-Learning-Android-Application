package com.odia.alphabet.draw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.odia.alphabet.model.AlphabetXY;

import java.util.ArrayList;

/**
 * Created by Awesome PC on 10-Dec-17.
 */
public class ShowPattern {

    AlphabetView alphabetView;
    ArrayList<AlphabetXY> alphabetXies;
    int jid = -1;
    int pId = 0;
    Point prevPoint = null;
    Path path = new Path();

    public ShowPattern(AlphabetView alphabetView,ArrayList<AlphabetXY> alphabetXies){
        this.alphabetView = alphabetView;
        this.alphabetXies = alphabetXies;
    }

    public Path drawPattern(Canvas canvas){


        for (AlphabetXY alphabetXY : alphabetXies) {

            int x = (int) alphabetXY.getX();
            int y = (int) alphabetXY.getY();
            Point point = new Point(x, y);

            if (jid != alphabetXY.getJid()) {
                jid = alphabetXY.getJid();
                pId = 0;
            }
            if (pId == 0) {
                path.moveTo(point.x, point.y);
            } else {
                float midX = (prevPoint.x + point.x) / 2;
                float midY = (prevPoint.y + point.y) / 2;
                if (pId == 1) {
                    path.lineTo(midX, midY);
                } else {
                    path.quadTo(prevPoint.x, prevPoint.y, midX, midY);
                }
            }
            pId++;
            prevPoint = point;
        }
        return path;
    }
}
