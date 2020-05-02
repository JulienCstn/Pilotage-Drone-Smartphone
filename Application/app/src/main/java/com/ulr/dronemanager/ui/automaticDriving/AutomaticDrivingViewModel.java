package com.ulr.dronemanager.ui.automaticDriving;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AutomaticDrivingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AutomaticDrivingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is automatic fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
