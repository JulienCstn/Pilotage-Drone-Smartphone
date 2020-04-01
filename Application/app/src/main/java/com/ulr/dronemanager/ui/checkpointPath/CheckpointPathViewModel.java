package com.ulr.dronemanager.ui.checkpointPath;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CheckpointPathViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public CheckpointPathViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is checkpoint fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
