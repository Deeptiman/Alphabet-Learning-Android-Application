package com.odia.alphabet.utils;


import com.odia.alphabet.draw.dimen.DrawDimensions;
import com.odia.alphabet.realm.RealmManager;

/**
 * Created by deeptiman on 14/12/2017.
 */

public class LocalizeHelper {

    public AppPreference appPreference;

    public LocalizeHelper() {
        init();
    }

    public void init(){
        appPreference = AppPreference.getAppPreferences(RealmManager.getContext());
    }

    public static String LOCALIZE_TXT = "localize";

    public String alphabetGridHeader = isLocalize() ? "JXÏÞA aÀàÒaÐ^" : "Odia Alphabet";
    public int alphabetGridHeaderSize = isLocalize() ? 30 : DrawDimensions.getAlphabetGridHeaderTextSize();

    public String write = isLocalize() ? "ÒmMÞaÐ" : "Write";
    public int writeSize = isLocalize() ? 20:14;

    public String learn = isLocalize() ? "hÞMÞaÐ" : "Learn";
    public int learnSize = isLocalize() ? 20:14;

    public String myDrawings = isLocalize() ? "ÒcÐe QÞ[Í" : "My Drawing";
    public int myDrawingSize = isLocalize() ? 20:14;
    public int historyMyDrawingSize = isLocalize() ? 28:18;


    public String overallRating = isLocalize() ? "jcæ]Ð¯ hÞMÞaÐ ^ÐeL" : "Overall Learning Rate";
    public int overallRatingSize = isLocalize() ? 22:14;

    public String finalScore = isLocalize() ? "A`Z* Ò²Ðe" : "Your Score";
    public int finalScoreSize = isLocalize() ? 30:20;

    public String fontName = isLocalize() ? "sarala" : "lohit";

    public boolean isLocalize() {
        if(appPreference==null)
            init();
        return appPreference.getBoolean(LOCALIZE_TXT, false);
    }

}
