package com.odia.alphabet.draw.dimen;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.odia.alphabet.R;
import com.odia.alphabet.realm.RealmManager;
import com.odia.alphabet.utils.LocalizeHelper;
import com.odia.alphabet.utils.RoundButton;
import com.odia.alphabet.utils.Utils;

/**
 * Created by Awesome PC on 23-Dec-17.
 */
public class DrawDimensions {

    static LocalizeHelper localizeHelper = new LocalizeHelper();

    static Context mContext = RealmManager.getContext();


    public static int getSplashScreenTextSize() {

        Context mContext = RealmManager.getContext();

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getInteger(R.integer.splash_screen_txt_size_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getInteger(R.integer.splash_screen_txt_size_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getInteger(R.integer.splash_screen_txt_size_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getInteger(R.integer.splash_screen_txt_size_xxxhdpi);
        }
        return mContext.getResources().getInteger(R.integer.splash_screen_txt_size_hdpi);
    }


    public static int getVersionTextSize() {

        Context mContext = RealmManager.getContext();

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getInteger(R.integer.version_code_txt_size_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getInteger(R.integer.version_code_txt_size_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getInteger(R.integer.version_code_txt_size_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getInteger(R.integer.version_code_txt_size_xxxhdpi);
        }
        return mContext.getResources().getInteger(R.integer.version_code_txt_size_hdpi);
    }

    public static void setAlphabetViewHeight(TextView alphabetView) {

        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) alphabetView.getLayoutParams();
        params.height = getAlphabetHeight();
        alphabetView.setLayoutParams(params);
    }

    private static int getAlphabetHeight() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.alphabet_height_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.alphabet_height_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.alphabet_height_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.alphabet_height_xxxhdpi);
        }
        return mContext.getResources().getDimensionPixelSize(R.dimen.alphabet_height_hdpi);
    }


    public static void setMyDrawingHeight(RelativeLayout parentLayout){

        RelativeLayout.LayoutParams parentParams = (RelativeLayout.LayoutParams) parentLayout.getLayoutParams();
        parentParams.height = getMyDrawingHeight();
        parentLayout.setLayoutParams(parentParams);
    }

    private static int getMyDrawingHeight() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.my_drawing_height_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.my_drawing_height_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.my_drawing_height_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.my_drawing_height_xxxhdpi);
        }
        return mContext.getResources().getDimensionPixelSize(R.dimen.my_drawing_height_hdpi);
    }


    public static void setDrawMenuLayoutWidth(RelativeLayout writeLayout, RelativeLayout learnLayout, RelativeLayout historyLayout) {

        LinearLayout.LayoutParams writeParams = (LinearLayout.LayoutParams) writeLayout.getLayoutParams();
        LinearLayout.LayoutParams learnParams = (LinearLayout.LayoutParams) learnLayout.getLayoutParams();
        LinearLayout.LayoutParams historyParams = (LinearLayout.LayoutParams) historyLayout.getLayoutParams();

        writeParams.width = getWriteDrawMenuLayoutWidth();
        learnParams.width = getLearnDrawMenuLayoutWidth();
        historyParams.width = getHistoryDrawMenuLayoutWidth();

        writeLayout.setLayoutParams(writeParams);
        learnLayout.setLayoutParams(learnParams);
        historyLayout.setLayoutParams(historyParams);
    }

    private static int getWriteDrawMenuLayoutWidth() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.write_layout_width_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.write_layout_width_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.write_layout_width_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.write_layout_width_xxxhdpi);
        }
        return mContext.getResources().getDimensionPixelSize(R.dimen.write_layout_width_hdpi);
    }

    private static int getLearnDrawMenuLayoutWidth() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.learn_layout_width_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.learn_layout_width_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.learn_layout_width_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.learn_layout_width_xxxhdpi);
        }
        return mContext.getResources().getDimensionPixelSize(R.dimen.learn_layout_width_hdpi);
    }


    private static int getHistoryDrawMenuLayoutWidth() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.history_layout_width_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.history_layout_width_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.history_layout_width_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.history_layout_width_xxxhdpi);
        }
        return mContext.getResources().getDimensionPixelSize(R.dimen.history_layout_width_hdpi);
    }

    public static void setDrawMenuTextSize(TextView writeTxt, TextView learnTxt, TextView myDrawingTxt) {
        writeTxt.setTextSize(localizeHelper.isLocalize() ? 20 : getDrawMenuTextSize());
        learnTxt.setTextSize(localizeHelper.isLocalize() ? 20 : getDrawMenuTextSize());
        myDrawingTxt.setTextSize(localizeHelper.isLocalize() ? 20 : getDrawMenuTextSize());
    }

    private static int getDrawMenuTextSize() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getInteger(R.integer.draw_menu_txt_size_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getInteger(R.integer.draw_menu_txt_size_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getInteger(R.integer.draw_menu_txt_size_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getInteger(R.integer.draw_menu_txt_size_xxxhdpi);
        }
        return mContext.getResources().getInteger(R.integer.draw_menu_txt_size_hdpi);
    }

    public static void setAlphabetGridTextSize(TextView alphabetGridText) {
        alphabetGridText.setTextSize(getAlphabetGridTextSize());
    }

    private static int getAlphabetGridTextSize() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getInteger(R.integer.alphabet_grid_txt_size_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getInteger(R.integer.alphabet_grid_txt_size_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getInteger(R.integer.alphabet_grid_txt_size_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getInteger(R.integer.alphabet_grid_txt_size_xxxhdpi);
        }
        return mContext.getResources().getInteger(R.integer.alphabet_grid_txt_size_hdpi);
    }


    public static int getAlphabetGridHeaderTextSize() {

        Context mContext = RealmManager.getContext();

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getInteger(R.integer.alphabet_grid_header_txt_size_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getInteger(R.integer.alphabet_grid_header_txt_size_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getInteger(R.integer.alphabet_grid_header_txt_size_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getInteger(R.integer.alphabet_grid_header_txt_size_xxxhdpi);
        }
        return mContext.getResources().getInteger(R.integer.alphabet_grid_header_txt_size_hdpi);
    }

    public static int getInfoTextSize() {

        Context mContext = RealmManager.getContext();

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getInteger(R.integer.info_txt_size_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getInteger(R.integer.info_txt_size_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getInteger(R.integer.info_txt_size_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getInteger(R.integer.info_txt_size_xxxhdpi);
        }
        return mContext.getResources().getInteger(R.integer.info_txt_size_hdpi);
    }


    public static int getInfoOdiaTextSize() {

        Context mContext = RealmManager.getContext();

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getInteger(R.integer.info_odia_txt_size_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getInteger(R.integer.info_odia_txt_size_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getInteger(R.integer.info_odia_txt_size_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getInteger(R.integer.info_odia_txt_size_xxxhdpi);
        }
        return mContext.getResources().getInteger(R.integer.info_odia_txt_size_hdpi);
    }



    public static void setOverallTextSize(TextView overallTextSize){
        overallTextSize.setTextSize(localizeHelper.isLocalize() ? 22:getOverallTextSize());
    }


    private static int getOverallTextSize() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getInteger(R.integer.overall_txt_size_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getInteger(R.integer.overall_txt_size_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getInteger(R.integer.overall_txt_size_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getInteger(R.integer.overall_txt_size_xxxhdpi);
        }
        return mContext.getResources().getInteger(R.integer.overall_txt_size_hdpi);
    }

    public static void setRoundButttonSize(RoundButton writeBtn, RoundButton learnBtn, RoundButton historyBtn) {

        ViewGroup.LayoutParams writeBtnparams = (ViewGroup.LayoutParams) writeBtn.getLayoutParams();
        ViewGroup.LayoutParams learnBtnparams = (ViewGroup.LayoutParams) learnBtn.getLayoutParams();
        ViewGroup.LayoutParams historyBtnparams = (ViewGroup.LayoutParams) historyBtn.getLayoutParams();

        writeBtnparams.width = getWriteRoundWidth();
        writeBtnparams.height = getWriteRoundHeight();

        learnBtnparams.width = getLearnRoundWidth();
        learnBtnparams.height = getLearnRoundHeight();

        historyBtnparams.width = getHistoryRoundWidth();
        historyBtnparams.height = getHistoryRoundHeight();

        writeBtn.setW(getCubicWriteRoundWidth());
        writeBtn.setH(getCubicWriteRoundHeight());

        learnBtn.setW(getCubicLearnRoundWidth());
        learnBtn.setH(getCubicLearnRoundHeight());

        historyBtn.setW(getCubicHistoryRoundWidth());
        historyBtn.setH(getCubicHistoryRoundHeight());

        writeBtn.setLayoutParams(writeBtnparams);
        learnBtn.setLayoutParams(learnBtnparams);
        historyBtn.setLayoutParams(historyBtnparams);
    }

    private static int getWriteRoundWidth() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.write_round_width_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.write_round_width_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.write_round_width_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.write_round_width_xxxhdpi);
        }
        return mContext.getResources().getDimensionPixelSize(R.dimen.write_round_width_hdpi);
    }

    private static int getWriteRoundHeight() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.write_round_height_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.write_round_height_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.write_round_height_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.write_round_height_xxxhdpi);
        }
        return mContext.getResources().getDimensionPixelSize(R.dimen.write_round_height_hdpi);
    }


    private static int getLearnRoundWidth() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.learn_round_width_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.learn_round_width_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.learn_round_width_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.learn_round_width_xxxhdpi);
        }
        return mContext.getResources().getDimensionPixelSize(R.dimen.learn_round_width_hdpi);
    }

    private static int getLearnRoundHeight() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.learn_round_height_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.learn_round_height_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.learn_round_height_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.learn_round_height_xxxhdpi);
        }
        return mContext.getResources().getDimensionPixelSize(R.dimen.learn_round_height_hdpi);
    }


    private static int getHistoryRoundWidth() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.history_round_width_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.history_round_width_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.history_round_width_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.history_round_width_xxxhdpi);
        }
        return mContext.getResources().getDimensionPixelSize(R.dimen.history_round_width_hdpi);
    }

    private static int getHistoryRoundHeight() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.history_round_height_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.history_round_height_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.history_round_height_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getDimensionPixelSize(R.dimen.history_round_height_xxxhdpi);
        }
        return mContext.getResources().getDimensionPixelSize(R.dimen.history_round_height_hdpi);
    }


    private static int getCubicWriteRoundWidth() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getInteger(R.integer.write_cub_round_width_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getInteger(R.integer.write_cub_round_width_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getInteger(R.integer.write_cub_round_width_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getInteger(R.integer.write_cub_round_width_xxxhdpi);
        }
        return mContext.getResources().getInteger(R.integer.write_cub_round_width_hdpi);
    }

    private static int getCubicWriteRoundHeight() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getInteger(R.integer.write_cub_round_height_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getInteger(R.integer.write_cub_round_height_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getInteger(R.integer.write_cub_round_height_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getInteger(R.integer.write_cub_round_height_xxxhdpi);
        }
        return mContext.getResources().getInteger(R.integer.write_cub_round_height_hdpi);
    }


    private static int getCubicLearnRoundWidth() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getInteger(R.integer.learn_cub_round_width_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getInteger(R.integer.learn_cub_round_width_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getInteger(R.integer.learn_cub_round_width_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getInteger(R.integer.learn_cub_round_width_xxxhdpi);
        }
        return mContext.getResources().getInteger(R.integer.learn_cub_round_width_hdpi);
    }

    private static int getCubicLearnRoundHeight() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getInteger(R.integer.learn_cub_round_height_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getInteger(R.integer.learn_cub_round_height_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getInteger(R.integer.learn_cub_round_height_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getInteger(R.integer.learn_cub_round_height_xxxhdpi);
        }
        return mContext.getResources().getInteger(R.integer.learn_cub_round_height_hdpi);
    }


    private static int getCubicHistoryRoundWidth() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getInteger(R.integer.history_cub_round_width_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getInteger(R.integer.history_cub_round_width_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getInteger(R.integer.history_cub_round_width_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getInteger(R.integer.history_cub_round_width_xxxhdpi);
        }
        return mContext.getResources().getInteger(R.integer.history_cub_round_width_hdpi);
    }

    private static int getCubicHistoryRoundHeight() {

        String screenSize = Utils.getDensityName(RealmManager.getContext());

        if (screenSize.equals("hdpi")) {
            return mContext.getResources().getInteger(R.integer.history_cub_round_height_hdpi);
        } else if (screenSize.equals("xhdpi")) {
            return mContext.getResources().getInteger(R.integer.history_cub_round_height_xhdpi);
        } else if (screenSize.equals("xxhdpi")) {
            return mContext.getResources().getInteger(R.integer.history_cub_round_height_xxhdpi);
        } else if (screenSize.equals("xxxhdpi")) {
            return mContext.getResources().getInteger(R.integer.history_cub_round_height_xxxhdpi);
        }
        return mContext.getResources().getInteger(R.integer.history_cub_round_height_hdpi);
    }


}
