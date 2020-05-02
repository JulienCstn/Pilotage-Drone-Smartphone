package com.ulr.dronemanager.ui.automaticDriving;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ulr.dronemanager.AutomaticDriving;
import com.ulr.dronemanager.ClientNMEA;
import com.ulr.dronemanager.MainActivity;
import com.ulr.dronemanager.Path;
import com.ulr.dronemanager.R;

public class AutomaticDrivingFragment extends Fragment implements OnMapReadyCallback {

    SupportMapFragment supportMapFragment;
    private GoogleMap mMapA;
    private Path pathReceive;

    private Button buttonGoBack;
    private Button buttonConnexionSimulateur;
    private AutomaticDriving monDroneAuto = new AutomaticDriving();

    //permet la recup des données envoyé par le fragment checkpoint
    private Path testPath;
    Bundle extras;
    //BroadCast qui recuperes larraylist de point des checkpointFragment
    //et doit servir a afficher le trace de celui-ci
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            if ("DATA_ACTION".equals(intent.getAction()) == true)
            {
                //Les données sont passées et peuvent être récupérées via :
                //intent.getSerializableExtra("DATA_EXTRA");
                //intent.getIntExtra("DATA_EXTRA", 2);
                extras = intent.getExtras(); // recup des extras
                //recuperations des donnees dans l'extras
                String test = extras.getString("DATA_EXTRA");
                if (test!=null){
                   System.out.println("text: "+test);
                }
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver, new IntentFilter("DATA_ACTION"));
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
       // LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_automatic_driving, container, false);

        buttonGoBack = root.findViewById(R.id.buttonGoBack);
        buttonConnexionSimulateur = root.findViewById(R.id.buttonConnexionSimulateur);

        if (buttonGoBack != null){
            buttonGoBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    monDroneAuto.goBackToFirstPoint();
                    System.out.println("GO BACK!!!!!!!!");

                }
            });
        }
        //lors du clique sur le bouton conenxion on demande ClientNMEA
        // de se conencter au simulateur et recuperer les trames
        if (buttonConnexionSimulateur != null){
            buttonConnexionSimulateur.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("SIMULATEUR NMEA:");
                    Intent intent = new Intent(view.getContext(), ClientNMEA.class);
                    startActivity(intent);
                }
            });
        }
        //Gestion du fragment MAP
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
        mMapA = googleMap;
        // Add a marker in larochelle, France, and move the camera.
        LatLng larochelle = new LatLng(46.144152,  -1.166624);
        mMapA.addMarker(new MarkerOptions().position(larochelle).title("Marker in LaRochelle"));//definir un marqueur
        mMapA.moveCamera(CameraUpdateFactory.newLatLng(larochelle)); //envoyer la camera Ã  la position
    }
}
