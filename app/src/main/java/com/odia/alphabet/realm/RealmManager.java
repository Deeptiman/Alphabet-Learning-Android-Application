package com.odia.alphabet.realm;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmResults;
import io.realm.RealmSchema;
import io.realm.Sort;

/**
 * Created by deeptiman on 21/11/2017.
 */

public class RealmManager {

    public static RealmConfiguration realmConfig;
    public static Realm realm;
    public static Context mContext;

    private static HashMap<String,ArrayList<OdiaAlphabetParcel>> mOdiaAlphabetParcelMap;

    public static void initialization(Context context){
        mContext = context;
        Realm.init(context);
        realmConfig = new RealmConfiguration.Builder()
                .schemaVersion(2)
                .migration(migration)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
        realm = Realm.getInstance(realmConfig);
    }

    static RealmMigration migration = new RealmMigration() {
        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            RealmSchema schema = realm.getSchema();
            try{

                schema.create("OdiaAlphabetMaster")
                        .addField("word", String.class)
                        .addField("data", String.class);
            } catch (Exception e){
                Log.d("RealmManager", "IllegalArgumentException : " + e.toString());
            }

        }
    };

    public static Context getContext(){
        return mContext;
    }

    public static Realm getRealm() {

        //realm = Realm.getInstance(realmConfig);
        return realm;
    }

    public static RealmResults getModelList(Class clazz){
        return getRealm().where(clazz).findAll();
    }

    public static RealmResults getSortedModelList(Class clazz,String fieldName){
        return getRealm().where(clazz).findAllSorted(fieldName, Sort.DESCENDING);
    }

    public static Object getModel(Class clazz,String fieldName,boolean value){
        return getRealm().where(clazz).findFirst();
    }

    public static RealmResults getAllModelList(Class clazz,String fieldName,boolean value){
        return getRealm().where(clazz).equalTo(fieldName, value).findAll();
    }

    public static RealmResults getAllModelList(Class clazz,String fieldName,String value){
        return getRealm().where(clazz).equalTo(fieldName, value).findAll();
    }

    public static RealmResults getAllModelList(Class clazz,String fieldName,int value){
        return getRealm().where(clazz).equalTo(fieldName, value).findAllSorted(fieldName, Sort.ASCENDING);
    }

    public static RealmResults getAllModelListDescending(Class clazz,String fieldName,int value){
        return getRealm().where(clazz).equalTo(fieldName, value).findAllSorted(fieldName, Sort.DESCENDING);
    }

    public static RealmResults getAllModelListDescending(Class clazz,String fieldName,String columnName,int value){
        return getRealm().where(clazz).equalTo(fieldName, value).findAllSorted(columnName, Sort.DESCENDING);
    }

    public static RealmResults getAllModelList(Class clazz,String fieldName){
        return getRealm().where(clazz).findAllSorted(fieldName, Sort.ASCENDING);
    }

    public static void clearTable(Realm realm,Class clazz){
        realm.where(clazz).findAll().deleteAllFromRealm();
    }

    public static void setOdiaAlphabetHashMap(HashMap<String,ArrayList<OdiaAlphabetParcel>> odiaAlphabetParcelMap){
        mOdiaAlphabetParcelMap = odiaAlphabetParcelMap;
    }

    public static HashMap<String,ArrayList<OdiaAlphabetParcel>> getOdiaAlphabetHashMap(){
        return mOdiaAlphabetParcelMap;
    }
}
