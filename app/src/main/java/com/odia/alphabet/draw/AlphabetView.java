package com.odia.alphabet.draw;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.odia.alphabet.R;
import com.odia.alphabet.model.AlphabetXY;
import com.odia.alphabet.model.DrawHistory;
import com.odia.alphabet.model.RatingStates;
import com.odia.alphabet.realm.DrawHistoryTransation;
import com.odia.alphabet.realm.RatingStateTransaction;
import com.odia.alphabet.realm.RealmManager;
import com.odia.alphabet.utils.AlphabetsList;
import com.odia.alphabet.utils.LocalizeHelper;
import com.odia.alphabet.utils.RatingCalculator;
import com.odia.alphabet.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by deeptiman on 7/12/2017.
 */

@SuppressLint("AppCompatCustomView")
public class AlphabetView extends TextView implements View.OnTouchListener, RatingStateTransaction.RatingStateCallback {

    Context mContext;
    int alphabetId;
    String word;
    Paint mDrawpaint, mLearnDrawPaint, mCircleJointPaint, mJointPaint;
    DashPathEffect dashPathEffect;
    Realm realm = RealmManager.getRealm();

    RealmResults<AlphabetXY> realmAlphabetXies;
    ArrayList<AlphabetXY> alphabetXies = new ArrayList<AlphabetXY>();

    Canvas canvas;

    PlayPattern playPattern;
    ShowPattern showPattern;
    GetTouchPoint getTouchPoint;
    RatingCalculator ratingCalculator;
    LocalizeHelper localizeHelper = new LocalizeHelper();

    Path defaultPath;
    Path userDrawPath = new Path();
    Path animPath = new Path();


    int DRAW_CMD = 0;
    int color = 0;
    int pId = 0;
    float pointX = 0, pointY = 0;
    Bitmap bitmap = null;
    int touchPoint = 0, ctouchPoint = 0, currentJoint = 0;

    int LEARNING_STATUS = 0;
    boolean COMPUTE_STARTED;
    boolean isDrawingFinished;

    Point point = new Point();
    Point prevPoint = new Point();

    float tx = 0, ty = 0;

    String[] jointColor = {"#D72E0C", "#1BA61D"};

    HashMap<Integer, Integer> ratingBucket;
    ArrayList<Integer> addRatings;

    ArrayList<Integer> jointIds;
    ArrayList<Float> savePointX, savePointY;
    //ArrayList<Double> angleList = new ArrayList<>();

    public AlphabetView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public AlphabetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public AlphabetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/sarala.ttf"));

        setTextColor(Color.parseColor("#DDE3E1"));
        setOnTouchListener(this);
        setDrawingCacheEnabled(true);
        buildDrawingCache(true);
        setupPaint();
        isDrawingFinished = false;
        ratingBucket = new HashMap<>();
        addRatings = new ArrayList<>();
        savePointX = new ArrayList<>();
        savePointY = new ArrayList<>();
        jointIds = new ArrayList<>();
        dashPathEffect = new DashPathEffect(new float[]{10.0f, 15.0f}, 2);
    }

    private void setupPaint() {

        mDrawpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDrawpaint.setColor(Color.BLACK);
        mDrawpaint.setAntiAlias(true);
        mDrawpaint.setStrokeWidth(5);
        mDrawpaint.setStyle(Paint.Style.STROKE);
        mDrawpaint.setStrokeCap(Paint.Cap.ROUND);
        mDrawpaint.setStrokeJoin(Paint.Join.ROUND);


        mLearnDrawPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLearnDrawPaint.setColor(Color.BLACK);
        mLearnDrawPaint.setAntiAlias(true);
        mLearnDrawPaint.setStrokeWidth(20);
        mLearnDrawPaint.setStyle(Paint.Style.STROKE);
        mLearnDrawPaint.setStrokeCap(Paint.Cap.ROUND);
        mLearnDrawPaint.setStrokeJoin(Paint.Join.ROUND);


        mJointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mJointPaint.setARGB(255, 0, 0, 0);
        mJointPaint.setTextAlign(Paint.Align.CENTER);
        mJointPaint.setTextSize(40);
        mJointPaint.setStyle(Paint.Style.STROKE);
        mJointPaint.setStrokeWidth(5);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setAlphabetId(int alphabetId) {
        this.alphabetId = alphabetId;
        setTextSize(TypedValue.COMPLEX_UNIT_SP,AlphabetsList.getAlphabetTextSize(alphabetId));
        setText(word);
    }

    public void setDrawCMD(int cmd) {
        DRAW_CMD = cmd;
        executeDrawCMD();
    }

    private void executeDrawCMD() {

        switch (DRAW_CMD) {
            case 1:
                if (ctouchPoint > 1) {

                    clearUserDraw();
                }
                animPath.reset();
                animPath = playPattern.drawPattern();
                break;
            case 2:

                playPattern.cancel();
                playPattern.clearPattern();
                clearUserDraw();

                break;
        }
    }

    public void learningEnd() {
        if (mContext instanceof DrawActivity) {
            ((DrawActivity) mContext).learningEnd();
        }
    }

    float x, y;

    public void setPlayPointXY(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setCurrentJoint(int currentJoint) {
        this.currentJoint = currentJoint;
    }

    boolean isDefaultPathShown;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        this.canvas = canvas;

        if (alphabetXies != null && alphabetXies.size() > 0) {

            if (!isDrawingFinished) {
                int currentJointFirstIndex = getCurrentJointFastIndex();
                int currentJointLastIndex = getCurrentJointLastIndex();

                int initialX = alphabetXies.get(currentJointFirstIndex).getX() + 50;
                int initialY = alphabetXies.get(currentJointFirstIndex).getY();

                int endX = alphabetXies.get(currentJointLastIndex).getX() - 50;
                int endY = alphabetXies.get(currentJointLastIndex).getY();

                int jId = currentJoint + 1;
                canvas.drawCircle(initialX, initialY - 5, 25, getCircleJointPaint(jointColor[0]));
                canvas.drawText("" + jId, initialX, initialY, getTextStokePaint());

                canvas.drawCircle(endX, endY - 5, 25, getCircleJointPaint(jointColor[1]));
                canvas.drawText("" + jId, endX, endY, getTextStokePaint());

            }

            if (!isDefaultPathShown) {
                this.defaultPath = showPattern.drawPattern(canvas);
                isDefaultPathShown = true;
            }

            mDrawpaint.setPathEffect(dashPathEffect);
            canvas.drawPath(defaultPath, mDrawpaint);
            canvas.drawPath(animPath, mLearnDrawPaint);

            Bitmap image = BitmapFactory.decodeResource(
                    getResources(),
                    R.drawable.chalk_32_1
            );

            if (DRAW_CMD == 1 && x != 0 && y != 0)
                canvas.drawBitmap(image, x, y, mLearnDrawPaint);

            if (DRAW_CMD == 2) {

                if (pId == 0 && point.x != 0 && point.y != 0) {
                    pId = 1;
                    userDrawPath.moveTo(point.x, point.y);
                } else if (pId == 1) {
                    userDrawPath.lineTo(point.x, point.y);
                    pId++;
                } else if (pId > 1) {

                    userDrawPath.quadTo(prevPoint.x, prevPoint.y, point.x, point.y);
                    COMPUTE_STARTED = true;
                    if (ctouchPoint == 0 || getTouchPoint.getPointStatus(ctouchPoint) == 0)
                        computeXY(pointX, pointY);
                }
                canvas.drawPath(userDrawPath, mLearnDrawPaint);
            }
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                return onTouchEvent(event.getX(), event.getY());

            case MotionEvent.ACTION_MOVE:

                return onTouchEvent(event.getX(), event.getY());
            default:
                break;
        }

        return true;
    }

    public void showJoints(String jointId, float x, float y) {
        canvas.drawText(jointId, x - 40, y - 20, mJointPaint);
    }

    private boolean onTouchEvent(float pointX, float pointY) {

        int px = (int) pointX;
        int py = (int) pointY;

        this.bitmap = getDrawingCache();

        if (px <= 0 || py <= 0) {
            return true;
        }

        color = -1;

        if (px < bitmap.getWidth() && py < bitmap.getHeight()) {
            int pixel = getPixel(px, py);

            if (Color.red(pixel) == 221 && Color.green(pixel) == 227 && Color.blue(pixel) == 225) {
                color = 1;
            }
        } else {
            return true;
        }

        this.pointX = pointX;
        this.pointY = pointY;

        touchPoint = getTouchPoint.getPoint(pointX, pointY);

        if (!isDrawingAllowed() || color != 1) {
            invalidate();
            return true;
        }

        COMPUTE_STARTED = true;

        prevPoint = new Point();

        if (point.x == 0 && point.y == 0) {
            prevPoint.set(px, py);
        } else {
            prevPoint.set(point.x, point.y);
        }
        point.set((int) pointX, (int) pointY);

        invalidate();

        return true;
    }

    private boolean isDrawingAllowed() {

        if (touchPoint != 0 && Math.abs(ctouchPoint - touchPoint) > 1) {
            return false;
        }
        return (touchPoint == 0 || alphabetXies.get(touchPoint - 1).getStatus() == 1) && alphabetXies.get(touchPoint).getStatus() == 0;
    }

    private Paint getCircleJointPaint(String color) {

        mCircleJointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleJointPaint.setColor(Color.parseColor(color));
        mCircleJointPaint.setAntiAlias(true);
        mCircleJointPaint.setStrokeWidth(20);
        mCircleJointPaint.setStyle(Paint.Style.FILL);
        mCircleJointPaint.setStrokeCap(Paint.Cap.ROUND);
        mCircleJointPaint.setStrokeJoin(Paint.Join.ROUND);
        return mCircleJointPaint;
    }

    private Paint getTextStokePaint() {
        Paint strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        strokePaint.setARGB(255, 255, 255, 255);
        strokePaint.setTextAlign(Paint.Align.CENTER);

        if (localizeHelper.isLocalize()) {
            strokePaint.setTextSize(50);
            strokePaint.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/sarala.ttf"));
        } else {
            strokePaint.setTextSize(30);
        }
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(5);
        return strokePaint;
    }

    private int getPixel(int px, int py) {
        return bitmap.getPixel(px, py);
    }

    private void computeXY(float pointX, float pointY) {

        if (COMPUTE_STARTED) {

            if (Math.abs(touchPoint - ctouchPoint) == 1) {
                AlphabetXY alphabetXY = alphabetXies.get(ctouchPoint);
                alphabetXY.setStatus(1);
                alphabetXies.set(ctouchPoint, alphabetXY);

                double x1 = pointX - tx;
                double y1 = pointY - ty;
                double angle = Math.abs(Math.atan2(x1, y1) / (Math.PI / 180));
                double saveAngle = Math.abs(alphabetXies.get(ctouchPoint).getAngle());
                double diff = Math.abs(angle - saveAngle);


                addRatings.add(ratingCalculator.getRating(diff));
                savePointX.add(pointX);
                savePointY.add(pointY);
                jointIds.add(currentJoint);

                int jointSize = getCurrentJointLastIndex() - 2;
                int totalSize = alphabetXies.size() - 2;

                if (ctouchPoint == jointSize) {

                    int index1 = ctouchPoint + 1;
                    double x11 = pointX - alphabetXies.get(index1).getX();
                    double y11 = pointY - alphabetXies.get(index1).getY();
                    double angle1 = Math.abs(Math.atan2(x11, y11) / (Math.PI / 180));
                    double saveAngle1 = Math.abs(alphabetXies.get(index1).getAngle());
                    double diff1 = Math.abs(angle1 - saveAngle1);

                    addRatings.add(ratingCalculator.getRating(diff1));
                    savePointX.add((float) alphabetXies.get(index1).getX());
                    savePointY.add((float) alphabetXies.get(index1).getY());
                    jointIds.add(currentJoint);

                    AlphabetXY alphabetXY1 = alphabetXies.get(index1);
                    alphabetXY1.setStatus(1);
                    alphabetXies.set(index1, alphabetXY1);


                    int index2 = ctouchPoint + 2;
                    double x21 = pointX - alphabetXies.get(index2).getX();
                    double y21 = pointY - alphabetXies.get(index2).getY();
                    double angle2 = Math.abs(Math.atan2(x21, y21) / (Math.PI / 180));
                    double saveAngle2 = Math.abs(alphabetXies.get(index2).getAngle());
                    double diff2 = Math.abs(angle2 - saveAngle2);

                    addRatings.add(ratingCalculator.getRating(diff2));
                    savePointX.add((float) alphabetXies.get(index2).getX());
                    savePointY.add((float) alphabetXies.get(index2).getY());
                    jointIds.add(currentJoint);

                    AlphabetXY alphabetXY2 = alphabetXies.get(index2);
                    alphabetXY2.setStatus(1);
                    alphabetXies.set(index2, alphabetXY2);

                    currentJoint++;
                    initialize();
                } else {
                    ctouchPoint++;
                    getTouchPoint.setCurrentTouchPoint(ctouchPoint);
                }

                if (ctouchPoint == totalSize - 1) {

                    for (int i = 0; i <= 5; i++) {
                        int c = 0;
                        for (int j = 0; j < addRatings.size(); j++) {
                            if (i == addRatings.get(j)) {
                                c++;
                            }
                        }
                        ratingBucket.put(i, c);
                    }
                    isDrawingFinished = true;
                    RatingStateTransaction ratingStateTransaction = new RatingStateTransaction();
                    ratingStateTransaction.execute(RatingStateTransaction.ADD_RATING_STATES, ratingBucket, alphabetXies, this);
                }

                tx = pointX;
                ty = pointY;
            }
        }
    }

    private void initialize() {
        ctouchPoint = getCurrentJointFastIndex();
        touchPoint = ctouchPoint;
        pId = 0;
        prevPoint.set(0, 0);
        point.set(0, 0);
    }

    private int getCurrentJointFastIndex() {
        int jId = 0;
        for (int i = 0; i < alphabetXies.size(); i++) {
            if (currentJoint == alphabetXies.get(i).getJid()) {
                jId = i;
                break;
            }
        }
        return jId;
    }

    private int getCurrentJointLastIndex() {

        int jId = 0;
        for (int i = 0; i < alphabetXies.size(); i++) {
            if (currentJoint == alphabetXies.get(i).getJid()) {
                jId = i;
            }
        }
        return jId;
    }

    public void clearUserDraw() {
        userDrawPath.reset();
        pId = 0;
        pointX = 0;
        pointY = 0;
        currentJoint = 0;
        ctouchPoint = 0;
        prevPoint.set(0, 0);
        point.set(0, 0);
        getTouchPoint.reset();
        isDrawingFinished = false;
        tx = 0;
        ty = 0;
        addRatings.clear();
        ratingBucket.clear();
        savePointX.clear();
        savePointY.clear();
        jointIds.clear();
        RealmManager.getRealm().beginTransaction();
        RealmManager.clearTable(RealmManager.getRealm(), RatingStates.class);
        RealmManager.getRealm().commitTransaction();
        for (AlphabetXY alphabetXY : alphabetXies) {
            alphabetXY.setStatus(0);
        }
        invalidate();
    }

    public void getAlphabetXY() {

        RealmResults<AlphabetXY> allRealmAlphabetXies = realm.where(AlphabetXY.class).equalTo("aId", alphabetId).findAllSorted("xyId", Sort.ASCENDING);

        for (int i = 0; i < allRealmAlphabetXies.size(); i++) {
            AlphabetXY alphabetXY = allRealmAlphabetXies.get(i);
        }

        realmAlphabetXies = realm.where(AlphabetXY.class).equalTo("aId", alphabetId).equalTo("screenSize", Utils.getDensityName(mContext)).findAllSorted("xyId", Sort.ASCENDING);

        alphabetXies.clear();
        int ax = 0, ay = 0;
        for (int i = 0; i < realmAlphabetXies.size(); i++) {

            int x1 = (int) realmAlphabetXies.get(i).getX() - ax;
            int y1 = (int) realmAlphabetXies.get(i).getY() - ay;

            float angle = (float) (Math.atan2(x1, y1) / (Math.PI / 180));

            AlphabetXY alphabetXY = new AlphabetXY();
            alphabetXY.setaId(realmAlphabetXies.get(i).getaId());
            alphabetXY.setJid(realmAlphabetXies.get(i).getJid());
            alphabetXY.setStatus(0);
            alphabetXY.setX(realmAlphabetXies.get(i).getX());
            alphabetXY.setY(realmAlphabetXies.get(i).getY());
            alphabetXY.setXyId(realmAlphabetXies.get(i).getXyId());
            alphabetXY.setAngle(angle);
            alphabetXY.setScreenSize(realmAlphabetXies.get(i).getScreenSize());
            alphabetXY.setCircularShape(realmAlphabetXies.get(i).getCircularShape());
            alphabetXies.add(alphabetXY);

            ax = realmAlphabetXies.get(i).getX();
            ay = realmAlphabetXies.get(i).getY();
        }

        playPattern = new PlayPattern(this, alphabetXies);
        showPattern = new ShowPattern(this, alphabetXies);
        getTouchPoint = new GetTouchPoint(this, alphabetXies);
        clearUserDraw();
        ratingCalculator = new RatingCalculator(mContext, alphabetXies.size());
        invalidate();
    }

    @Override
    public void onSuccess() {

        DrawActivity drawActivity = (DrawActivity) mContext;

        int drawId = RealmManager.getAllModelList(DrawHistory.class, "alphaId", alphabetId).size() + 1;

        DrawHistoryTransation drawHistoryTransation = new DrawHistoryTransation();
        drawHistoryTransation.execute(DrawHistoryTransation.ADD_DRAW_HISTORY, savePointX, savePointY, jointIds,
                ratingCalculator.calculateRating(), alphabetId, drawId, getDrawingCache(true), drawActivity.getSaveCallBack());
    }
}
