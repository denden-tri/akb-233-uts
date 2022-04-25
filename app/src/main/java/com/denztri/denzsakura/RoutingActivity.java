package com.denztri.denzsakura;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import com.denztri.denzsakura.ui.pager.ScreenSlidePagerActivity;

public class RoutingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        splashScreen.setKeepOnScreenCondition(() -> true);
        checkFirstRun();
    }


    public void checkFirstRun(){
        final String PREFS_NAME = "PrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;

        // Get current version code
        int currVerCode = BuildConfig.VERSION_CODE;

        // Get saved version code
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVerCode = preferences.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);

        // Check for first run or upgrade
        if (currVerCode == savedVerCode){
            startActivity(new Intent(this, MainActivity.class));
        } else if (savedVerCode == DOESNT_EXIST){
            startActivity(new Intent(this, ScreenSlidePagerActivity.class));
        } else if (currVerCode > savedVerCode){
            startActivity(new Intent(this, ScreenSlidePagerActivity.class));
        }

        // Update the shared preference
        preferences.edit().putInt(PREF_VERSION_CODE_KEY, currVerCode).apply();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finishAffinity();
        finish();
    }
}
