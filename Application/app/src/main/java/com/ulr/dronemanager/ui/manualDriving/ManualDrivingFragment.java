package com.ulr.dronemanager.ui.manualDriving;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ulr.dronemanager.R;

public class ManualDrivingFragment extends Fragment implements SensorEventListener {

    private ManualDrivingViewModel manualDrivingViewModel;
    float x,y,z;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    private TextView mTxtViewX;
    private TextView mTxtViewY;
    private TextView mTxtViewZ;

    public ManualDrivingFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        manualDrivingViewModel = ViewModelProviders.of(this).get(ManualDrivingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_manual_driving, container, false);

        /*
        final TextView textView = root.findViewById(R.id.text_manualDriving);
        manualDrivingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE); //creation du manager de sensor
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {

            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);// on recupere le sensor accelerometre
            sensorManager.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            // pas d'accelerometre
        }
        mTxtViewX = (TextView) root.findViewById(R.id.textX);
        mTxtViewY = (TextView) root.findViewById(R.id.textY);
        mTxtViewZ = (TextView) root.findViewById(R.id.textZ);

        return root;
    }
    public void Position(float iX, float iY, float iZ)
    {
        mTxtViewZ.setText(" "+iZ);
        mTxtViewX.setText(" "+iX);
        mTxtViewY.setText(" "+iY);

    }

    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];
        Position(x,y,z);//donner les différentes valeurs des axes

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
