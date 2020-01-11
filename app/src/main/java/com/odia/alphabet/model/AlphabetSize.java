package com.odia.alphabet.model;

import io.realm.RealmObject;

/**
 * Created by Awesome PC on 23-Dec-17.
 */
public class AlphabetSize extends RealmObject {

    private int textSize;
    private int alphabetId;
    private String screenSize;

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getAlphabetId() {
        return alphabetId;
    }

    public void setAlphabetId(int alphabetId) {
        this.alphabetId = alphabetId;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }
}
