package com.ulr.dronemanager.ui.manualDriving;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ManualDrivingViewModel extends ViewModel {
    private MutableLiveData<String> inputX, inputY, inputZ;


    public ManualDrivingViewModel() {
        inputX = new MutableLiveData<>();
        //mText.setValue("This is manual fragment");
    }

    public LiveData<String> getText() {
        return inputX;
    }
}
