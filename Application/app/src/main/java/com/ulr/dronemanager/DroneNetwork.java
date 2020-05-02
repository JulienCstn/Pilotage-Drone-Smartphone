package com.ulr.dronemanager;

import java.util.ArrayList;

public class DroneNetwork {

    /* Comment marche les trames */
    /* Une trame commence toujours par un " $ "
       Puis suivie de 2 lettres pour identifier le récepteur:
        - GP -> Global Positioning System
        - LC -> Loran-C Recevier
        - OM -> Omega Navigation Receiver
        - II -> Integrated Instrumentation
       Puis suivie de 3 lettres (GGA,GLL,GSA,GSV,VTG,RMC) pour identifier la trame:
        nous nous intéresserons à la trame RMC qui indique le positionnement géographique longitude-latitude
        ainsi qu'une vitesse de déplacement et le cap
       Ensuite plusieurs champs séparés par des virgules, qui nous permettrons de récuperer les données souhaitées
       Ensuite un checksum, indiqué par un " * "
       Puis la fin de la trame indiquée par retour chariot CR où bien un retour à la ligne LF

       Les trames qui nous interesse sont alors : GPRMC
     */

    /* Attributes */
    private ArrayList <String> infos;

    /* pour les trames GPRMC */
    private String acquisitionDuFix;

    private String latitudeDegre;
    private String latitude;
    private String orientationLat;

    private String longitudeDegre;
    private String longitude;
    private String orientationLon;

    private String capVrai;
    private String vitesseNoeud;


    /* Functions */

    public void recupererInformation() {
        String test = "$GPRMC,225446,A,4916.45,N,12311.12,W,000.5,054.7,191194,020.3,E*68";

        /* la trame GPRMC est la première de l'ArrayList */
        /* exemple de trame -->  $GPRMC , 225446 ,  A   , 4916.45 ,  N  ,  12311.12  ,  W  ,  000.5  , 054.7 , 191194 , 020.3 ,  E*68 */
        /*                       sep[0]   sep[1]  sep[2]  sep[3]   sep[4]   sep[5]   sep[6]   sep[7]   sep[8]  sep[9]   sep[10]  sep[11]        */
        // String[] sep = this.infos.get(0).split(",");
        String[] sep = test.split(",");
        /* on récupère l'heure */
        this.acquisitionDuFix = sep[1].substring(0,2) + ":" + sep[1].substring(2,4) + ":" + sep[1].substring(4,6);
        System.out.println(this.acquisitionDuFix);

        /* on récupére les données de latitude */
        this.latitudeDegre = sep[3].substring(0, 2); // 2 premiers caractères;
        this.latitude = sep[3].substring(2, 7); // 5 caracteres d'apres;
        this.orientationLat = sep[4];

        /* on récupère les données de longitude */
        this.longitudeDegre = sep[5].substring(0, 2); // deux premiers caracteres;
        this.longitude = sep[5].substring(2, 7); // 5 caracteres d'apres;
        this.orientationLon = sep[6];

        /* on récupére la vitesse et le cap */
        this.vitesseNoeud = sep[7];
        this.capVrai = sep[8];

    }
    public Path sendPath(Path aPath){
        return aPath;
    }

    public String getEnvironemental(){
        return "";
    }

}


