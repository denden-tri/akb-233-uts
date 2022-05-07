package com.denztri.denzsakura.ui.daily;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 25-04-2022
 **/

public class DailyViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<String> mText;

    public DailyViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("This is daily fragment");
    }

    public LiveData<String> getText(){return mText;}
}