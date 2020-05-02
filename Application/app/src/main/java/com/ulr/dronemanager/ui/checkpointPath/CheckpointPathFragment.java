package com.ulr.dronemanager.ui.checkpointPath;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ulr.dronemanager.MainActivity;
import com.ulr.dronemanager.Path;
import com.ulr.dronemanager.R;
import com.ulr.dronemanager.Waypoint;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class CheckpointPathFragment extends Fragment implements OnMapReadyCallback {
    private CheckpointPathViewModel checkpointPathViewModel;

    SupportMapFragment supportMapFragment;
    private GoogleMap mMap;
    private Marker marker;

    private Path pathToFollow;
    private int startPoint;
    private int finishPoint;

    private Button buttonSendPath;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        checkpointPathViewModel = ViewModelProviders.of(this).get(CheckpointPathViewModel.class);
        View root = inflater.inflate(R.layout.fragment_checkpoint_path, container, false);

        pathToFollow = new Path();
        //Envoi des donnees du pathFollow vers l'autre fragement (automaticFragment)
        buttonSendPath = root.findViewById(R.id.buttonSendPath);
        if (buttonSendPath != null){
            buttonSendPath.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //On envoie les données au fragment au click sur le bouton
                    final Intent intent = new Intent("DATA_ACTION");
                    intent.putExtra("DATA_EXTRA", pathToFollow.toString()); //put les donnees dans le intent
                    // on envoie le flux de donnees du intent
                    LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
                }
            });
        }


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
                MarkerOptions marker = new MarkerOptions().position(new LatLng(point.latitude,point.longitude));
                marker.title(String.valueOf(marker.getPosition()));

                //createCheckpoint(marker.getPosition().latitude, marker.getPosition().longitude,String.valueOf(marker.getRotation()));
                createCheckpoint(marker.getPosition().latitude, marker.getPosition().longitude,"N");
                System.out.println("path to follow: "+pathToFollow.toString());
                System.out.println(marker.getRotation());
                mMap.addMarker(marker);
                //test marqueur
                //System.out.println(marker.getPosition());// donne la position du point créé


            }
        });

    }


    public int getStartPoint() {
        return this.startPoint;
    }

    public int getFinishPoint() {
        return this.finishPoint;
    }

    public void setPath(Path aPath) {
        this.pathToFollow = aPath;
    }

    /*
       méthode qui récupère l'endroit où l'utilisateur a cliqué sur la map
       converti les données en créant une instance de waypoint
       ce waypoint sera envoyé directement au path
       path qui sera utilisé pour choisir le chemin à envoyer directement à l'automaticdrivingactivity
     */
    public void createCheckpoint(double lat, double lon, String cardi) {
        Waypoint wp = new Waypoint(lat, lon, cardi);
        pathToFollow.getWaypoints().add(wp);
        // envoyer à l'automaticdrivingactivity
    }

    /*
    Méthode qui va pouvoir charger un chemin qui est déjà en GPX
     */
    public Path chargerChemin(String file) throws ParserConfigurationException, IOException, SAXException {
        ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();

        File fichier = new File(file);
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(fichier);

        document.getDocumentElement().normalize();

        Element root = document.getDocumentElement();
        NodeList waypointsList = document.getElementsByTagName("wpt");
        String name = root.getFirstChild().getTextContent();
        for(int i = 0; i < waypointsList.getLength(); i++) {
            Node wp = waypointsList.item(i);
            if(wp.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) wp;
                float latitude = Float.parseFloat(element.getAttribute("lat"));
                float longitude = Float.parseFloat(element.getAttribute("lon"));
                Waypoint actualWaypoint = new Waypoint(latitude, longitude, null);
                waypoints.add(actualWaypoint);
            }
        }

        Path loadedPath = new Path(name, waypoints, null);
        this.pathToFollow = loadedPath;
        //TODO a verifier si c'est bien ce que voulais renvoyer kylian
        return this.pathToFollow;
    }

}
