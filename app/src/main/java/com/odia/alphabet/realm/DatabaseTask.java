package com.odia.alphabet.realm;

import android.app.Activity;

import com.odia.alphabet.usecase.UseCase;

import java.util.ArrayList;

/**
 * Created by Awesome PC on 02-May-18.
 */
public class DatabaseTask extends UseCase<DatabaseTask.RequestValue,DatabaseTask.ResponseValue,DatabaseTask.TaskFinished>{

    static String TAG = "ODIADATABASEHELPER";

    Activity activity;

    public DatabaseTask(Activity activity){
        this.activity = activity;
    }


    @Override
    protected void executeUseCase(final RequestValue requestValues) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DatabaseHelper databaseHelper = new DatabaseHelper();
                databaseHelper.processDB(requestValues.aId, requestValues.word, requestValues.odiaAlphabetParcels, new DatabaseHelper.DatabaseCallback() {
                    @Override
                    public void onDatabaseSuccess() {
                        getUseCaseCallback().onSuccess(new ResponseValue(requestValues.word));
                    }

                    @Override
                    public void onDatabaseError() {
                        getUseCaseCallback().onError();
                    }
                });
            }
        });


    }

    @Override
    protected void taskFinished(TaskFinished finished) {

    }

    public static class RequestValue implements UseCase.RequestValues {

        private int aId;
        private String word;
        private ArrayList<OdiaAlphabetParcel> odiaAlphabetParcels;

        public RequestValue(int aId, String word,ArrayList<OdiaAlphabetParcel> odiaAlphabetParcels) {
            this.aId = aId;
            this.word = word;
            this.odiaAlphabetParcels = odiaAlphabetParcels;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {
        private String word;
        public ResponseValue(String word){
            this.word = word;
        }

        public String getWord() {
            return word;
        }
    }

    public static class TaskFinished implements UseCase.TaskFinished{

        public TaskFinished(){

        }
    }


}
