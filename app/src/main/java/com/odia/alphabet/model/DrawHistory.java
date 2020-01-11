package com.odia.alphabet.model;

import io.realm.RealmObject;

/**
 * Created by deeptiman on 12/12/2017.
 */

public class DrawHistory extends RealmObject {

    private float savePointX;
    private float savePointY;

    private float rating;
    private int alphaId;
    private int jointId;
    private int drawId;

    public float getSavePointX() {
        return savePointX;
    }

    public void setSavePointX(float savePointX) {
        this.savePointX = savePointX;
    }

    public float getSavePointY() {
        return savePointY;
    }

    public void setSavePointY(float savePointY) {
        this.savePointY = savePointY;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getAlphaId() {
        return alphaId;
    }

    public void setAlphaId(int alphaId) {
        this.alphaId = alphaId;
    }

    public int getDrawId() {
        return drawId;
    }

    public void setDrawId(int drawId) {
        this.drawId = drawId;
    }

    public int getJointId() {
        return jointId;
    }

    public void setJointId(int jointId) {
        this.jointId = jointId;
    }

}
