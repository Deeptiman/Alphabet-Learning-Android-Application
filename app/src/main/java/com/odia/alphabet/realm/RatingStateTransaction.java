package com.odia.alphabet.realm;

import com.odia.alphabet.model.AlphabetXY;
import com.odia.alphabet.model.RatingStates;

import java.util.ArrayList;
import java.util.HashMap;

import io.realm.Realm;

/**
 * Created by deeptiman on 11/12/2017.
 */

public class RatingStateTransaction implements Realm.Transaction, Realm.Transaction.OnSuccess, Realm.Transaction.OnError {

    int transactionType;
    HashMap<Integer, Integer> ratingBucket;
    ArrayList<AlphabetXY> alphabetXies;
    RatingStateCallback ratingStateCallback;
    public static int ADD_RATING_STATES = 0;

    Realm realm;

    public void execute(int transactionType, HashMap<Integer, Integer> ratingBucket, ArrayList<AlphabetXY> alphabetXies,RatingStateCallback ratingStateCallback) {
        this.transactionType = transactionType;
        this.ratingBucket = ratingBucket;
        this.alphabetXies = alphabetXies;
        this.ratingStateCallback = ratingStateCallback;
        try {
            RealmManager.getRealm().executeTransactionAsync(this, this, this);
        } catch (Exception e) {

        }
    }

    @Override
    public void execute(Realm realm) {
        this.realm = realm;
        switch (transactionType) {
            case 0:
                for (int i = 0; i <= 5; i++) {
                    RatingStates ratingStates = realm.createObject(RatingStates.class);
                    ratingStates.setStarIndex(i);
                    ratingStates.setStarCount(ratingBucket.get(i));
                }
                break;
        }
    }

    @Override
    public void onError(Throwable error) {
    }

    @Override
    public void onSuccess() {
        ratingStateCallback.onSuccess();
    }

    public interface RatingStateCallback{
        public void onSuccess();
    }
}
