package com.ulr.dronemanager;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Path {

    private String pathName;
    private ArrayList<Waypoint> waypoints;
    private Date dateOfCreation;

    public Path(String name, ArrayList<Waypoint> waypoints, Date date) {
        this.pathName = name;
        this.waypoints = new ArrayList<Waypoint>();;
        this.dateOfCreation = date;
    }

    public String getName() {
        return this.pathName;
    }

    public void setPathName(String name) {
        this.pathName = name;
    }

    public ArrayList<Waypoint> getWaypoints() {
        return this.waypoints;
    }

    public void setDate(Date date) {
        this.dateOfCreation = date;
    }

    public Date getDate() {
        return this.dateOfCreation;
    }

    /**
     * Méthode qui va permettre de créer un XML (gpx) qui contiendra les données du trajet.
     * @param aPath
     */
    public void savePath(Path aPath) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("gpx");
            document.appendChild(root);

            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(aPath.getName()));
            root.appendChild(name);

            for(int i = 0; i < aPath.getWaypoints().size(); i++) {
                Element wpt = document.createElement("wpt");
                root.appendChild(wpt);

                Attr lat = document.createAttribute("lat");
                lat.setValue(toString(aPath.getWaypoints().get(i).getLatitude()));
                wpt.setAttributeNode(lat);

                Attr lon = document.createAttribute("lon");
                lon.setValue(toString(aPath.getWaypoints().get(i).getLongitude()));
                wpt.setAttributeNode(lon);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(aPath.getName() + ".gpx"));

            transformer.transform(domSource, streamResult);

            System.out.println("[!] Création du GPX.");

        } catch (ParserConfigurationException | TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private String toString(float unFloat) {
        return toString(unFloat);
    }

}
