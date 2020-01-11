package com.odia.alphabet.utils;

import android.content.Context;

import com.odia.alphabet.draw.DrawActivity;
import com.odia.alphabet.model.RatingStates;
import com.odia.alphabet.realm.RealmManager;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by deeptiman on 12/12/2017.
 */

public class RatingCalculator {

    Context mContext;
    Realm realm;
    int pointSize;

    public RatingCalculator(Context context, int pointSize) {
        this.mContext = context;
        this.pointSize = pointSize;
        this.realm = RealmManager.getRealm();
    }

    public float calculateRating() {

        float rating = 0;

        RatingStates fiveStarRatingStates = realm.where(RatingStates.class).greaterThanOrEqualTo("starCount", pointSize - 10).equalTo("starIndex", 5).findFirst();

        if (fiveStarRatingStates != null) {
            rating = 5;
        } else {

            int shrink = 0;
            RealmResults<RatingStates> shrinkRatingStates = realm.where(RatingStates.class).lessThan("starIndex",3).findAllSorted("starCount", Sort.DESCENDING);
            for (int i = 0; i < shrinkRatingStates.size(); i++) {
                if ((shrinkRatingStates.get(i).getStarIndex() == 0 && shrinkRatingStates.get(i).getStarCount() >= 3) ||
                        (shrinkRatingStates.get(i).getStarIndex() == 1 && shrinkRatingStates.get(i).getStarCount() >= 5) ||
                        (shrinkRatingStates.get(i).getStarIndex() == 2 && shrinkRatingStates.get(i).getStarCount() >= 7)) {
                    shrink++;
                }
            }

            RatingStates fiveRatingStates = realm.where(RatingStates.class).equalTo("starIndex", 5).findFirst();

            RealmResults<RatingStates> goodRatingStates = realm.where(RatingStates.class).greaterThan("starIndex", 2).notEqualTo("starIndex", 5).findAllSorted("starCount", Sort.DESCENDING);

            int max = 0, index = 0;
            int[] indexes = new int[goodRatingStates.size()];
            int[] counts = new int[goodRatingStates.size()];
            int c = 0;
            for (int i = 0; i < goodRatingStates.size(); i++) {
                if (max < goodRatingStates.get(i).getStarCount()) {
                    max = goodRatingStates.get(i).getStarCount();
                    index = goodRatingStates.get(i).getStarIndex();
                    indexes[c] = index;
                    counts[c] = max;
                    c++;
                }
            }

            float goodRate = 0;
            if (counts[0] == counts[1] || (fiveRatingStates.getStarCount()<=pointSize/2 && fiveRatingStates.getStarCount()>=(pointSize/2)-10)) {
                goodRate = 3.5f;
            } else if (counts[0] > counts[1]) {
                goodRate = indexes[0];
            } else {
                goodRate = indexes[1];
            }

            rating = goodRate - shrink;
        }


        if (mContext instanceof DrawActivity) {
            ((DrawActivity) mContext).getRating(rating);
        }
        return rating;
    }


    public int getRating(double value) {

        if (value <= 10) {
            return 5;
        } else if (value > 10 && value <= 15) {
            return 4;
        } else if (value > 15 && value <= 25) {
            return 3;
        } else if (value > 25 && value <= 35) {
            return 2;
        } else if (value > 35 && value <= 50) {
            return 1;
        } else if (value > 50) {
            return 0;
        }

        return 0;
    }
}
