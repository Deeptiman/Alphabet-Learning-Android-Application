package com.odia.alphabet.utils;


import com.odia.alphabet.model.Alphabet;
import com.odia.alphabet.model.AlphabetSize;
import com.odia.alphabet.realm.RealmManager;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by deeptiman on 6/12/2017.
 */

public class AlphabetsList {

    static Realm realm = RealmManager.getRealm();

    public static RealmResults<Alphabet> getAlphabetList() {

        if (RealmManager.getModelList(Alphabet.class).size() != 50)
            addAlphabets();
        return RealmManager.getModelList(Alphabet.class);
    }

    final static ArrayList<String> alphabets = new ArrayList<String>();

    private static void addAlphabets() {

        getAlphabet();

        realm.beginTransaction();
        for (int i = 0; i < alphabets.size(); i++) {
            int id = i + 1;
            String word = alphabets.get(i);

            Alphabet checkIfExists = realm.where(Alphabet.class).equalTo("id", id).findFirst();
            if (checkIfExists == null) {
                Alphabet alphabet = realm.createObject(Alphabet.class);
                alphabet.setId(id);
                alphabet.setAlphabet(word);
                alphabet.setScore(0);
            }
        }
        realm.commitTransaction();
    }

    public static void getAlphabet() {
        alphabets.add("@");
        alphabets.add("A");
        alphabets.add("B");
        alphabets.add("C");
        alphabets.add("D");
        alphabets.add("E");
        alphabets.add("F");
        alphabets.add("G");
        alphabets.add("H");
        alphabets.add("I");
        alphabets.add("J");
        alphabets.add("K");
        alphabets.add("L");
        alphabets.add("M");
        alphabets.add("N");
        alphabets.add("O");
        alphabets.add("P");
        alphabets.add("Q");
        alphabets.add("R");
        alphabets.add("S");
        alphabets.add("T");
        alphabets.add("U");
        alphabets.add("V");
        alphabets.add("W");
        alphabets.add("X");
        alphabets.add("Y");
        alphabets.add("Z");
        alphabets.add("a");
        alphabets.add("b");
        alphabets.add("c");
        alphabets.add("d");
        alphabets.add("e");
        alphabets.add("f");
        alphabets.add("g");
        alphabets.add("h");
        alphabets.add("i");
        alphabets.add("j");
        alphabets.add("k");
        alphabets.add("l");
        alphabets.add("m");
        alphabets.add("0");
        alphabets.add("1");
        alphabets.add("2");
        alphabets.add("3");
        alphabets.add("4");
        alphabets.add("5");
        alphabets.add("6");
        alphabets.add("7");
        alphabets.add("8");
        alphabets.add("9");
    }

    public static int getAlphabetPos(String alphabet) {
        for (int i = 0; i < alphabets.size(); i++) {
            String alpha = alphabets.get(i);
            if (alpha.equals(alphabet))
                return i;
        }
        return 0;
    }

    public static String getAlphabet(int pos) {
        getAlphabet();
        return alphabets.get(pos);
    }

    public static int getAlphabetTextSize(int alphabetId) {

        AlphabetSize alphabetSize = RealmManager.getRealm().where(AlphabetSize.class).equalTo("alphabetId", alphabetId).equalTo("screenSize", Utils.getDensityName(RealmManager.getContext())).findFirst();

        return alphabetSize.getTextSize();
    }

}
