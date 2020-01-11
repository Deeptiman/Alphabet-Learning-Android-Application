package com.odia.alphabet.history;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.odia.alphabet.R;
import com.odia.alphabet.model.DrawSavePointXY;
import com.odia.alphabet.realm.RealmManager;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by deeptiman on 7/12/2017.
 */

@SuppressLint("AppCompatCustomView")
public class HistoryAlphabetView extends TextView {

    Context mContext;
    int alphabetId, drawId;
    Paint mDrawpaint, mLearnDrawPaint, mJointPaint;
    DashPathEffect dashPathEffect;
    Realm realm = RealmManager.getRealm();

    RealmResults<DrawSavePointXY> realmDrawHistories;

    Canvas canvas;
    int storkeWidth = 5;

    HistoryShowPattern historyShowPattern;
    HistoryPlayPattern historyPlayPattern;
    Path animPath = new Path();
    Path defaultPath;

    int DRAW_CMD = 0;

    ArrayList<Float> savePointX, savePointY;

    public HistoryAlphabetView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public HistoryAlphabetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public HistoryAlphabetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/sarala.ttf"));
        setTextColor(Color.parseColor("#FFFFFF"));
        setupPaint();
        savePointX = new ArrayList<>();
        savePointY = new ArrayList<>();
        dashPathEffect = new DashPathEffect(new float[]{10.0f, 15.0f}, 2);
    }

    private void setupPaint() {

        mDrawpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDrawpaint.setColor(defaultPathColor);
        mDrawpaint.setAntiAlias(true);
        mDrawpaint.setStyle(Paint.Style.STROKE);
        mDrawpaint.setStrokeCap(Paint.Cap.ROUND);
        mDrawpaint.setStrokeJoin(Paint.Join.ROUND);

        mLearnDrawPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLearnDrawPaint.setColor(Color.parseColor("#CCCCCC"));
        mLearnDrawPaint.setAntiAlias(true);
        mLearnDrawPaint.setStrokeWidth(20);
        mLearnDrawPaint.setStyle(Paint.Style.STROKE);
        mLearnDrawPaint.setStrokeCap(Paint.Cap.ROUND);
        mLearnDrawPaint.setStrokeJoin(Paint.Join.ROUND);
    }

    public int getStorkeWidth() {
        return storkeWidth;
    }

    public void setStorkeWidth(int storkeWidth) {
        this.storkeWidth = storkeWidth;
        mDrawpaint.setStrokeWidth(storkeWidth);
    }

    int liftUp;
    int shifLeft;

    public void setLiftUp(int liftUp) {
        this.liftUp = liftUp;
    }

    private int getLiftUp() {
        return liftUp;
    }

    public int getShifLeft() {
        return shifLeft;
    }

    public void setShifLeft(int shifLeft) {
        this.shifLeft = shifLeft;
    }

    int defaultPathColor = Color.parseColor("#DDE3E1");

    public void setDefaultPathColor(int defaultPathColor) {
        this.defaultPathColor = defaultPathColor;
        mDrawpaint.setColor(defaultPathColor);
    }

    public void setAlphabetId(int alphabetId) {
        this.alphabetId = alphabetId;
    }

    public void setDrawId(int drawId) {
        this.drawId = drawId;
    }

    float x, y;

    public void setPlayPointXY(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setDrawCMD(int cmd) {
        DRAW_CMD = cmd;
        executeDrawCMD();
    }

    private void executeDrawCMD() {

        switch (DRAW_CMD) {
            case 1:
                animPath.reset();
                animPath = historyPlayPattern.drawPattern();
                break;

        }
    }

    boolean isDefaultPathShown;
    boolean showChalk;

    public boolean isShowChalk() {
        return showChalk;
    }

    public void setShowChalk(boolean showChalk) {
        this.showChalk = showChalk;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        this.canvas = canvas;

        if (realmDrawHistories != null && realmDrawHistories.size() > 0) {

            if (!isDefaultPathShown) {
                this.defaultPath = historyShowPattern.drawPattern();
                isDefaultPathShown = true;
            }
            canvas.drawPath(defaultPath, mDrawpaint);
            canvas.drawPath(animPath, mLearnDrawPaint);

            if (isShowChalk()) {
                Bitmap image = BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.chalk_32_1
                );

                canvas.drawBitmap(image, x, y, mLearnDrawPaint);
            }
        }
    }

    public void getDrawHistoryXY() {
        realmDrawHistories = realm.where(DrawSavePointXY.class).equalTo("alphaId", alphabetId).equalTo("drawId", drawId).findAll();
        historyPlayPattern = new HistoryPlayPattern(this, realmDrawHistories);
        historyShowPattern = new HistoryShowPattern(this, realmDrawHistories, getLiftUp(),getShifLeft());
        invalidate();
    }

}
