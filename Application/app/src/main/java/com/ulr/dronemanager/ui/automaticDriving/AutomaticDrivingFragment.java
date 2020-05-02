package com.ulr.dronemanager.ui.automaticDriving;

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

public class AutomaticDrivingFragment extends Fragment {
    private AutomaticDrivingViewModel automaticDrivingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        automaticDrivingViewModel = ViewModelProviders.of(this).get(AutomaticDrivingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_automatic_driving, container, false);

        final TextView textView = root.findViewById(R.id.text_automaticDriving);
        automaticDrivingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}
