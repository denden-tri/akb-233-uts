package com.denztri.denzsakura.ui.media.tab.music;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MusicViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<String> mText;

    public MusicViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is music fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}