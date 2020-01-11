package com.odia.alphabet.learningrate;

import com.odia.alphabet.model.DrawHistory;
import com.odia.alphabet.realm.RealmManager;

import java.text.DecimalFormat;

import io.realm.RealmResults;

/**
 * Created by deeptiman on 13/12/2017.
 */

public class LearningRate {

    public static int getLearningRate(int aid) {

        RealmResults<DrawHistory> drawHistories = RealmManager.getAllModelListDescending(DrawHistory.class, "alphaId", aid);

        if (drawHistories.size() == 0)
            return 0;

        float rating = 0;
        for (int i = 0; i < drawHistories.size(); i++) {
            rating += drawHistories.get(i).getRating();
        }

        float avg = rating / (drawHistories.size() * 5);
        DecimalFormat format = new DecimalFormat();
        format.setDecimalSeparatorAlwaysShown(false);

        return Math.round(avg * 10);
    }

}
