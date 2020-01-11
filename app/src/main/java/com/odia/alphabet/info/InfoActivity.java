package com.odia.alphabet.info;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.odia.alphabet.R;
import com.odia.alphabet.draw.dimen.DrawDimensions;
import com.odia.alphabet.utils.Utils;


public class InfoActivity extends AppCompatActivity {

    TextView odiaTxt1,odiaTxt2, appInfoTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        odiaTxt1 = (TextView) findViewById(R.id.odia_txt1);
        odiaTxt2 = (TextView) findViewById(R.id.odia_txt2);
        appInfoTxt = (TextView) findViewById(R.id.app_info_txt);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/sarala.ttf");
        odiaTxt1.setTypeface(myTypeface);
        odiaTxt2.setTypeface(myTypeface);
        odiaTxt1.setText("L");
        odiaTxt1.setTextSize(300);

        odiaTxt2.setText("MNO");


        appInfoTxt.setText("\"Odia Alphabet\" is an odia letter handwritting practice app. It will be helpful for people to learn Odia letters and improve their handwritting. The app has learning module, which will help to learn and understand the exact writting of a letter. \n" +
                "The app will rating after finishing each writting session. User can also check history of their writting, whenever they want.We hope this app give user best learning experience for Odia letter writting. ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        odiaTxt2.setTextSize(DrawDimensions.getInfoOdiaTextSize());
        appInfoTxt.setTextSize(DrawDimensions.getInfoTextSize());
        Utils.isMaximumResolution(this);
    }
}
