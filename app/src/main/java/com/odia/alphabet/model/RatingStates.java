package com.odia.alphabet.model;

import io.realm.RealmObject;

/**
 * Created by deeptiman on 11/12/2017.
 */

public class RatingStates extends RealmObject{

    private int starIndex;
    private int starCount;

    public int getStarIndex() {
        return starIndex;
    }

    public void setStarIndex(int starIndex) {
        this.starIndex = starIndex;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }
}
