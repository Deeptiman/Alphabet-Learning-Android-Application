package com.odia.alphabet.security.decrypt;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.common.collect.Iterators;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.odia.alphabet.realm.DatabaseHelper;
import com.odia.alphabet.realm.DatabaseTask;
import com.odia.alphabet.realm.OdiaAlphabetCoordinate;
import com.odia.alphabet.realm.OdiaAlphabetParcel;
import com.odia.alphabet.realm.OdiaAlphabetXY;
import com.odia.alphabet.realm.RealmManager;
import com.odia.alphabet.security.AESDemo;
import com.odia.alphabet.security.RSAHelper;
import com.odia.alphabet.usecase.UseCase;
import com.odia.alphabet.usecase.UseCaseHandler;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Awesome PC on 01-May-18.
 */
public class DecryptHelper {

    String TAG = "ODIADATABASEHELPER";

    public static int totalAlphabet = 50;

    static {
        System.loadLibrary("OdiaAlphabet");
    }
    public static String PUBLIC_KEY = getRSAPublicKey();
    public static String PRIVATE_KEY = getRSAPrivateKey();

    public DecryptHelper() {
        initRSA();
    }

    public void startDecryptProcess(String data,DecryptHelperCallback decryptHelperCallback) {

        String aesInnerKey = rsadecryptText(getInnerKey());
        String aesOutterKey = rsadecryptText(getOutterKey());

        Gson gson = new Gson();
        HashMap<String,HashMap<String,ArrayList<OdiaAlphabetParcel>>> odiaAlphabetParcelMap = new HashMap<>();
        String aesOutterData = aesdecryptText(aesOutterKey, data);

        try {

            JSONObject jsonObject = new JSONObject(aesOutterData);

            Iterator<?> keys = jsonObject.keys();

            int size = totalAlphabet;
            int progress = 0;
            while (keys.hasNext()) {

                final String key = (String) keys.next();

                String dec = aesdecryptText(aesInnerKey, (String) jsonObject.get(key));

                OdiaAlphabetXY odiaAlphabetXY = gson.fromJson(dec, OdiaAlphabetXY.class);

                int hdpiTextSize = odiaAlphabetXY.getHdpi().getTextSize();
                int xhdpiTextSize = odiaAlphabetXY.getXhdpi().getTextSize();
                int xxhdpiTextSize = odiaAlphabetXY.getXxhdpi().getTextSize();
                int xxxhdpiTextSize = odiaAlphabetXY.getXxxhdpi().getTextSize();

                String hdpiCoordinates = gson.toJson(odiaAlphabetXY.getHdpi().getCoordinates());
                String xhdpiCoordinates = gson.toJson(odiaAlphabetXY.getXhdpi().getCoordinates());
                String xxhdpiCoordinates = gson.toJson(odiaAlphabetXY.getXxhdpi().getCoordinates());
                String xxxhdpiCoordinates = gson.toJson(odiaAlphabetXY.getXxxhdpi().getCoordinates());


                Type listType = new TypeToken<ArrayList<OdiaAlphabetCoordinate.CoordinatesBean>>() {
                }.getType();

                List<OdiaAlphabetCoordinate.CoordinatesBean> hdpiCoordinatesBean = new Gson().fromJson(hdpiCoordinates, listType);
                List<OdiaAlphabetCoordinate.CoordinatesBean> xhdpiCoordinatesBean = new Gson().fromJson(xhdpiCoordinates, listType);
                List<OdiaAlphabetCoordinate.CoordinatesBean> xxhdpiCoordinatesBean = new Gson().fromJson(xxhdpiCoordinates, listType);
                List<OdiaAlphabetCoordinate.CoordinatesBean> xxxhdpiCoordinatesBean = new Gson().fromJson(xxxhdpiCoordinates, listType);

                OdiaAlphabetParcel odiaAlphabetParcelhdpi = new OdiaAlphabetParcel();
                OdiaAlphabetParcel odiaAlphabetParcelxhdpi = new OdiaAlphabetParcel();
                OdiaAlphabetParcel odiaAlphabetParcelxxhdpi = new OdiaAlphabetParcel();
                OdiaAlphabetParcel odiaAlphabetParcelxxxhdpi = new OdiaAlphabetParcel();

                odiaAlphabetParcelhdpi.setScreenSize("hdpi");
                odiaAlphabetParcelhdpi.setTextSize(hdpiTextSize);
                odiaAlphabetParcelhdpi.setCoordinatesBeanList(hdpiCoordinatesBean);

                odiaAlphabetParcelxhdpi.setScreenSize("xhdpi");
                odiaAlphabetParcelxhdpi.setTextSize(xhdpiTextSize);
                odiaAlphabetParcelxhdpi.setCoordinatesBeanList(xhdpiCoordinatesBean);

                odiaAlphabetParcelxxhdpi.setScreenSize("xxhdpi");
                odiaAlphabetParcelxxhdpi.setTextSize(xxhdpiTextSize);
                odiaAlphabetParcelxxhdpi.setCoordinatesBeanList(xxhdpiCoordinatesBean);

                odiaAlphabetParcelxxxhdpi.setScreenSize("xxxhdpi");
                odiaAlphabetParcelxxxhdpi.setTextSize(xxxhdpiTextSize);
                odiaAlphabetParcelxxxhdpi.setCoordinatesBeanList(xxxhdpiCoordinatesBean);

                ArrayList<OdiaAlphabetParcel> odiaAlphabetParcelArrayList = new ArrayList<>();

                odiaAlphabetParcelArrayList.add(odiaAlphabetParcelhdpi);
                odiaAlphabetParcelArrayList.add(odiaAlphabetParcelxhdpi);
                odiaAlphabetParcelArrayList.add(odiaAlphabetParcelxxhdpi);
                odiaAlphabetParcelArrayList.add(odiaAlphabetParcelxxxhdpi);

                HashMap<String,ArrayList<OdiaAlphabetParcel>> subMap = new HashMap<>();
                subMap.put(key,odiaAlphabetParcelArrayList);
                odiaAlphabetParcelMap.put(key,subMap);

                progress++;

                decryptHelperCallback.onDecryptProgress(progress, size);

                if(progress==size)
                    decryptHelperCallback.onDecryptFinish(odiaAlphabetParcelMap);

                Thread.sleep(300);
            }

        } catch (Exception e) {
        }
    }

    private void initRSA() {
        try {
            Map<String, Object> keyMap = RSAHelper.initKey();
            PUBLIC_KEY = RSAHelper.getPublicKey(keyMap);
        } catch (Exception e) {
        }
    }

    private String aesdecryptText(String key, String enc) {
        try {
            return AESDemo.decrypt(enc, key);
            //return AESHelper.decrypt(key, enc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Failed";
    }

    private String rsadecryptText(String encodeStr) {

        String decodeStr = "";
        try {
            byte[] decodeData = RSAHelper.decryptByPrivateKey(RSAHelper.hexStr2Bytes(encodeStr), PRIVATE_KEY);
            decodeStr = new String(decodeData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodeStr;
    }

    public interface DecryptHelperCallback{
        public void onDecryptProgress(int progress,int total);
        public void onDecryptFinish(HashMap<String,HashMap<String,ArrayList<OdiaAlphabetParcel>>> odiaAlphabetParcelMap);
    }


    public native static String getInnerKey();

    public native static String getOutterKey();

    public native static String getRSAPublicKey();

    public native static String getRSAPrivateKey();
}
