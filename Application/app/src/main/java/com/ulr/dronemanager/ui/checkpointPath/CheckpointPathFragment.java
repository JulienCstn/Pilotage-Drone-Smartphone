package com.ulr.dronemanager.ui.checkpointPath;

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

public class CheckpointPathFragment extends Fragment {
    private CheckpointPathViewModel checkpointPathViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        checkpointPathViewModel =
                ViewModelProviders.of(this).get(CheckpointPathViewModel.class);
        View root = inflater.inflate(R.layout.fragment_checkpoint_path, container, false);
        final TextView textView = root.findViewById(R.id.text_checkpointPath);
        checkpointPathViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
