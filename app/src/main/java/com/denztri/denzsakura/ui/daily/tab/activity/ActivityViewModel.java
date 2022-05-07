package com.denztri.denzsakura.ui.daily.tab.activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.denztri.denzsakura.db.Activity;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 25-04-2022
 **/

public class ActivityViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<Activity> cycleData;

    public MutableLiveData<Activity> getCycleData() {
        if (cycleData == null) {
            cycleData = new MutableLiveData<>();
        }
        return cycleData;
    }

    public void loadActivity(){

    }
}