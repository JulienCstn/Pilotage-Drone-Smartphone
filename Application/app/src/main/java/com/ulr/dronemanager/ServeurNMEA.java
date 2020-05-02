package com.ulr.dronemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServeurNMEA extends Activity {

    private Socket serveur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> alTrame = new ArrayList<>();
        Intent intentServeurToSimulator = getIntent();
        if (intentServeurToSimulator.hasExtra("alTrame")) {
            alTrame = intentServeurToSimulator.getStringArrayListExtra("alTrame");
        }

        System.out.println("SERVEUR");



        sendTrameToNMEA sendMessageTask = new sendTrameToNMEA();
        sendMessageTask.execute();

    }

    private class sendTrameToNMEA extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {

                ArrayList<String> alTrameTest = new ArrayList<>();
                String t1 = "$GPRMC,210957.385,A,3459.75,S,13830.08,E,10,13.0,230420,,,*08";
                String t2 = "$IIVHW,13.0,T,13.0,M,5.4,N,9.9,K*54";
                String t3 = "$IIHDT,13.0,T*10";
                String t4 = "$GPGLL,3459.75,S,13830.08,E,210957.385,A*27";
                String t5 = "$GPGGA,210957.385,3459.75,S,13830.08,E,1,4,1.4,2.0,M,,,,*2F";
                String t6 = "$GPGSA,A,3,8,11,15,22,,,,,,,,,1.2,1.4,2.1*0B";
                String t7 = "$GPZDA,210957.385,23,04,2020,+02,00*7C";
                String t8 = "!AIVDO,1,1,,A,17PaewhP0mar0SEcvJL@P@Ij0000,0*31";
                String t9 = "$WIMWV,295.4,T,9.2,N,A*24";
                String t10 = "$WIMWD,295.4,T,295.4,M,9.2,N,4.7,M*52";
                String t11 = "$WIMWV,309.3,R,22.5,M,A*1C";
                String t12 = "$IIMTW,8.2,C*29";
                String t13 = "$SDDPT,9.3,01*72";
                String t14 = "$SDDBT,30.4,f,9.3,M,5.1,F*3F";
                String t15 = "$SDDBK,30.4,f,9.3,M,5.1,F*20";
                String t16 = "$SDDBS,30.4,f,9.3,M,5.1,F*38";
                String t17 = "!AIVDO,2,1,9,A,57Paewh00001<To7;?@plD5<Tl0000000000000U1@:551;m92TnA3QF,0*66";
                String t18 = "!AIVDO,2,2,9,A,@00000000000002,2*5D";
                String t19 = "$IIRPM,E,1,0,10.5,A*7C";
                String t20 = "$IIRPM,E,2,0,10.5,A*7F";

                alTrameTest.add(t1);
                alTrameTest.add(t2);
                alTrameTest.add(t3);
                alTrameTest.add(t4);
                alTrameTest.add(t5);
                alTrameTest.add(t6);
                alTrameTest.add(t7);
                alTrameTest.add(t8);
                alTrameTest.add(t9);
                alTrameTest.add(t10);
                alTrameTest.add(t11);
                alTrameTest.add(t12);
                alTrameTest.add(t13);
                alTrameTest.add(t14);
                alTrameTest.add(t15);
                alTrameTest.add(t16);
                alTrameTest.add(t17);
                alTrameTest.add(t18);
                alTrameTest.add(t19);
                alTrameTest.add(t20);


                String IP = "127.0.0.1";
                int fileAttente = 100;
                System.out.println(InetAddress.getByName(IP).toString());

                ServerSocket socketS = new ServerSocket(55555, fileAttente, InetAddress.getByName(IP));
                System.out.println(socketS.toString());
                Socket serveur = socketS.accept();

                //System.out.println("CA MARCHE !!!!!!!!!!!!!!!!!!!!!!!!");

                PrintWriter outToClient = new PrintWriter(new OutputStreamWriter(serveur.getOutputStream()));
                for (int i = 0; i < alTrameTest.size(); i++) {
                    outToClient.write(alTrameTest.get(i));
                }

                outToClient.close();
                outToClient.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
