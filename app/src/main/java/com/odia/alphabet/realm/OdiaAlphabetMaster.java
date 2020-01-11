package com.odia.alphabet.realm;

import io.realm.RealmObject;

/**
 * Created by Awesome PC on 03-May-18.
 */
public class OdiaAlphabetMaster extends RealmObject {

    private String word;
    private String data;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
