package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setAppLocale("fr");

        setContentView(R.layout.activity_main);
    }

    private void setAppLocale(String localeC){
        Resources r = getResources();
        DisplayMetrics d = r.getDisplayMetrics();
        Configuration c = r.getConfiguration();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 ){
            c.setLocale(new Locale(localeC.toLowerCase()));
        }else{
           c.locale = new Locale(localeC.toLowerCase());
        }
        r.updateConfiguration(c,d);
    }
}
