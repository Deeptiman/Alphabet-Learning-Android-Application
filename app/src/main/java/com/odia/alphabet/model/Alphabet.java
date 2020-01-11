package com.odia.alphabet.model;

import io.realm.RealmObject;

/**
 * Created by deeptiman on 6/12/2017.
 */

public class Alphabet extends RealmObject {

    private int id;
    private int score;
    private String alphabet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(String alphabet) {
        this.alphabet = alphabet;
    }
}
