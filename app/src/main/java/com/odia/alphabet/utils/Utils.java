package com.odia.alphabet.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.odia.alphabet.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by deeptiman on 7/12/2017.
 */

public class Utils {

    static Dialog dialog;
    Context mContext;

    public Utils(Context context) {
        this.mContext = context;
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showDialog(Activity activity, String msg) {

        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.loading_dialog);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        AVLoadingIndicatorView avLoadingIndicatorView = (AVLoadingIndicatorView) dialog.findViewById(R.id.avi);
        text.setText(msg);

        avLoadingIndicatorView.show();
        dialog.show();

    }

    public static void dismissDialog() {
        dialog.dismiss();
    }

    public static byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public static Bitmap convertByteArrayToBitmap(byte[] byteArray) {
        /*Bitmap bmp = Bitmap.createBitmap(150, 150, Bitmap.Config.RGB_565);
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        buffer.rewind();
        bmp.copyPixelsFromBuffer(buffer);
        return bmp;*/

        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    public static void isMaximumResolution(Activity activity) {
        if (getScreenDensity(activity) > 3.3) {
            showScreenErrorDialog(activity);
        }
    }

    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static String getDensityName(Context context) {
        float density = context.getResources().getDisplayMetrics().density;

        if (density > 3.0) {
            return "xxxhdpi"; // Larger
        }
        if (density == 3.0) {
            return "xxhdpi"; // Large
        }
        if (density >= 2.5) {
            return "xhdpi"; // medium
        }
        if (density >= 1.5) {
            return "hdpi"; //small
        }
        return "ldpi";
    }

    public static void showScreenErrorDialog(final Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Sorry, this resolution doesn't support ")
                .setCancelable(false)
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        activity.finish();
                    }
                });

        AlertDialog alert = builder.create();
        alert.setTitle("Odia Alphabet");
        alert.setCancelable(false);
        alert.show();
    }


    public String loadDataFromAsset() {
        String json = null;
        try {
            InputStream is = mContext.getAssets().open("file");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            Log.d("ODIAALPHABET", "loadDataFromAsset : Exception : " + ex.toString());
            return null;
        }
        return json;
    }


    public static void exitApp(Activity activity) {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(a);
    }
}
