package com.odia.alphabet.model;

import io.realm.RealmObject;

/**
 * Created by deeptiman on 6/12/2017.
 */

public class AlphabetXY extends RealmObject {

    private int x;
    private int y;

    private double angle;
    private int aId;
    private int xyId;
    private int jid;
    private int status;
    private String screenSize;

    private int circularShape;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public int getXyId() {
        return xyId;
    }

    public void setXyId(int xyId) {
        this.xyId = xyId;
    }

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public int getCircularShape() {
        return circularShape;
    }

    public void setCircularShape(int circularShape) {
        this.circularShape = circularShape;
    }
}
