package com.odia.alphabet.draw;

import android.graphics.Path;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;

import com.odia.alphabet.model.AlphabetXY;

import java.util.ArrayList;

/**
 * Created by Awesome PC on 10-Dec-17.
 */
public class PlayPattern {

    AlphabetView alphabetView;
    ArrayList<AlphabetXY> alphabetXies;
    int animId = 0, ct = 0;
    Point animPrevPoint = new Point();
    Path animPath = new Path();

    Thread myThread;
    ArrayList<Point> points;

    public PlayPattern(AlphabetView alphabetView, ArrayList<AlphabetXY> alphabetXies) {
        this.alphabetView = alphabetView;
        this.alphabetXies = alphabetXies;
        this.points = new ArrayList<Point>();
        addPoints();
    }

    private void addPoints() {
        for (int i = 0; i < alphabetXies.size(); i++) {
            points.add(new Point((int) alphabetXies.get(i).getX(), (int) alphabetXies.get(i).getY()));
        }
    }

    final int[] jid = {0};
    final int[] i = {0};

    public Path drawPattern() {

        points.clear();
        addPoints();
        animPrevPoint.set(0, 0);
        animId = 0;
        jid[0] = 0;
        i[0] = 0;

        final Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                Point point = points.get(i[0]);

                float midX = (animPrevPoint.x + point.x) / 2;
                float midY = (animPrevPoint.y + point.y) / 2;

                if (jid[0] != alphabetXies.get(i[0]).getJid()) {
                    jid[0] = alphabetXies.get(i[0]).getJid();
                    animId = 0;
                    alphabetView.setCurrentJoint(jid[0]);
                }

                if (animId == 0) {
                    animPath.moveTo(point.x, point.y);
                } else if (animId == 1) {
                    animPath.lineTo(midX, midY);
                } else {
                    animPath.quadTo(animPrevPoint.x, animPrevPoint.y, midX, midY);
                }
                alphabetView.setPlayPointXY((float)alphabetXies.get(i[0]).getX(),(float)alphabetXies.get(i[0]).getY());
                animPrevPoint = point;
                alphabetView.invalidate();
                animId++;
                i[0]++;
            }
        };

        myThread = new Thread(new Runnable() {
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {

                    if (i[0] >= points.size()) {
                        alphabetView.learningEnd();
                        break;
                    }

                    try {
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(150);
                    } catch (Throwable t) {
                    }
                }
            }
        });

        myThread.start();

        return animPath;
    }

    public void clearPattern() {

        points.clear();
        animPrevPoint.set(0, 0);
        animId = 0;
        jid[0] = 0;
        i[0] = 0;
        animPath.reset();
        alphabetView.invalidate();
    }

    public void cancel() {

        if (myThread != null)
            myThread.interrupt();
    }

}
