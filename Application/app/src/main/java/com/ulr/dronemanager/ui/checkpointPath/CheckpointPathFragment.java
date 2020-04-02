package com.ulr.dronemanager.ui.checkpointPath;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ulr.dronemanager.R;

public class CheckpointPathFragment extends Fragment implements OnMapReadyCallback {
    private CheckpointPathViewModel checkpointPathViewModel;
    SupportMapFragment supportMapFragment;
    private GoogleMap mMap;
    private Marker marker;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        checkpointPathViewModel = ViewModelProviders.of(this).get(CheckpointPathViewModel.class);
        View root = inflater.inflate(R.layout.fragment_checkpoint_path, container, false);
        /*final TextView textView = root.findViewById(R.id.text_checkpointPath);
        checkpointPathViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if(supportMapFragment == null){

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            supportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.map, supportMapFragment).commit();
        }
        supportMapFragment.getMapAsync(this);


        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in larochelle, France, and move the camera.
        LatLng larochelle = new LatLng(46.144152,  -1.166624);
        mMap.addMarker(new MarkerOptions().position(larochelle).title("Marker in LaRochelle"));//definir un marqueur
        mMap.moveCamera(CameraUpdateFactory.newLatLng(larochelle)); //envoyer la camera Ã  la position
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                MarkerOptions marker = new MarkerOptions().position(new LatLng(point.latitude,point.longitude)).title("New Marker");
                mMap.addMarker(marker);
                System.out.println(marker.getPosition());// donne la position du point créé
            }
        });

    }

}
