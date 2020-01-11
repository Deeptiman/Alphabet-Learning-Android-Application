package com.odia.alphabet.security.decrypt;

import com.odia.alphabet.realm.OdiaAlphabetParcel;
import com.odia.alphabet.usecase.UseCase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Awesome PC on 03-May-18.
 */
public class DecryptTask extends UseCase<DecryptTask.RequestValue,DecryptTask.ResponseValue,DecryptTask.TaskFinished>{

    String TAG = "ODIADATABASEHELPER";
    DecryptTaskCallback decryptTaskCallback;
    public DecryptTask(DecryptTaskCallback decryptTaskCallback){
        this.decryptTaskCallback = decryptTaskCallback;
    }

    @Override
    protected void executeUseCase(RequestValue requestValues) {

        String encryptData = requestValues.getEncryptData();

        new DecryptHelper().startDecryptProcess(encryptData, new DecryptHelper.DecryptHelperCallback() {
            @Override
            public void onDecryptProgress(int progress, int total) {
                decryptTaskCallback.onPublishProgress(progress,total);
            }

            @Override
            public void onDecryptFinish(HashMap<String,HashMap<String,ArrayList<OdiaAlphabetParcel>>> odiaAlphabetParcelMap) {
              getUseCaseCallback().onSuccess(new ResponseValue(odiaAlphabetParcelMap));
            }
        });
    }

    @Override
    protected void taskFinished(TaskFinished finished) {

    }

    public static class RequestValue implements UseCase.RequestValues {

        String encryptData;
        public RequestValue(String encryptData) {
            this.encryptData = encryptData;
        }

        public String getEncryptData() {
            return encryptData;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {

        HashMap<String,HashMap<String,ArrayList<OdiaAlphabetParcel>>> odiaAlphabetParcelMap;
        public ResponseValue(HashMap<String,HashMap<String,ArrayList<OdiaAlphabetParcel>>> odiaAlphabetParcelMap){
           this.odiaAlphabetParcelMap = odiaAlphabetParcelMap;
        }

        public HashMap<String, HashMap<String, ArrayList<OdiaAlphabetParcel>>> getOdiaAlphabetParcelMap() {
            return odiaAlphabetParcelMap;
        }
    }

    public static class TaskFinished implements UseCase.TaskFinished{

        public TaskFinished(){

        }
    }

    public interface DecryptTaskCallback{
        public void onPublishProgress(int progress, int total);
    }

}
