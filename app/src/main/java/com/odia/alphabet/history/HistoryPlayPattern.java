package com.odia.alphabet.history;

import android.graphics.Path;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;

import com.odia.alphabet.model.DrawSavePointXY;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by Awesome PC on 10-Dec-17.
 */
public class HistoryPlayPattern {

    HistoryAlphabetView historyAlphabetView;
    RealmResults<DrawSavePointXY> drawHistories;
    int animId = 0, ct = 0;
    Point animPrevPoint = new Point();
    Path animPath = new Path();

    Thread myThread;
    ArrayList<Point> points;

    public HistoryPlayPattern(HistoryAlphabetView historyAlphabetView, RealmResults<DrawSavePointXY> drawHistories) {
        this.historyAlphabetView = historyAlphabetView;
        this.drawHistories = drawHistories;
        this.points = new ArrayList<Point>();
        addPoints();
    }

    private void addPoints() {
        for (int i = 0; i < drawHistories.size(); i++) {
            points.add(new Point((int) drawHistories.get(i).getSavePointX(), (int) drawHistories.get(i).getSavePointY()));
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

                if (jid[0] != drawHistories.get(i[0]).getJointId()) {
                    jid[0] = drawHistories.get(i[0]).getJointId();
                    animId = 0;
                }

                if (animId == 0) {
                    animPath.moveTo(point.x, point.y);
                } else {

                    int nextDrawId = i[0] + 1;
                    if (nextDrawId < drawHistories.size() && jid[0] != drawHistories.get(nextDrawId).getJointId()) {
                        animId = 0;
                    }

                    if (animId == 1) {
                        animPath.lineTo(midX, midY);
                    } else if (animId > 1) {
                        animPath.quadTo(animPrevPoint.x, animPrevPoint.y, midX, midY);
                    }
                }
                historyAlphabetView.setPlayPointXY((float) drawHistories.get(i[0]).getSavePointX(), (float) drawHistories.get(i[0]).getSavePointY());
                animPrevPoint = point;
                historyAlphabetView.invalidate();
                animId++;
                i[0]++;
            }
        };

        myThread = new Thread(new Runnable() {
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {

                    if (i[0] >= points.size()) {
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
        historyAlphabetView.invalidate();
    }

    public void cancel() {

        if (myThread != null)
            myThread.interrupt();
    }

}
