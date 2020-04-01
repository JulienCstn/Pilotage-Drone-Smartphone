package com.ulr.dronemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CheckPointActivity extends AppCompatActivity {

    private Path pathToFollow;
    private int startPoint;
    private int finishPoint;

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
    public void createCheckpoint() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_point);
    }
}
