package com.denztri.denzsakura.ui.daily.tab.activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.denztri.denzsakura.db.Activity;

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