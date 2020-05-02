package com.ulr.dronemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
       // Waypoint wp = new Waypoint(/* pos(lat click user*/, /* pos(lon click user*/, /* pos(cardi click user*/);
        //this.pathToFollow.getWaypoints().add(wp);
        // envoyer à l'automaticdrivingactivity
    }

    /*
    Méthode qui va pouvoir charger un chemin qui est déjà en GPX
     */
    /*
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
    }
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_point);
    }
}
