package com.ulr.dronemanager;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientNMEA extends AppCompatActivity {

    private Socket client;
    private PrintWriter printwriter;
    private Button button;
    private String message_sortant;
    private BufferedReader in_bufferedReader;
    private String message_entrant;
    private InputStreamReader in_streamReader;
    TextView trameNMEA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_automatic_driving, R.id.navigation_manual_driving, R.id.navigation_checkpoint_path)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        System.out.println("CLIENT NMEA");
        //recup les trames
        SendMessage sendMessageTask = new SendMessage();
        sendMessageTask.execute();
        message_sortant = ("AsyncTask Creee"); // Reset the text field to blank

    }

    private class SendMessage extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {

                //Log.d("Connexion","Demande de connexion");
                client = new Socket("192.168.1.60 ", 55555);// connect to the server


                in_bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                ArrayList<String> alTrame = new ArrayList<String>();
                String line;


                int arret = 0;
                while (arret < 20) {
                    line = in_bufferedReader.readLine();
                    alTrame.add(line);
                    arret++;
                }

                for(int i = 0; i < alTrame.size(); i++){
                    System.out.println(alTrame.get(i));
                } //Afficher trame dans ArrayList


                Intent intentClientToDrone = new Intent(ClientNMEA.this, DroneNetwork.class);
                intentClientToDrone.putExtra("alTrame", alTrame);
                startActivity(intentClientToDrone);


                client.close();

            } catch (Exception e) {
            }
            return null;
        }
    }

}

