package com.ulr.dronemanager;

public class Waypoint {

    private double latitude;
    private double longitude;
    private String cardinalité;

    public Waypoint(double lat, double lon, String cardi) {
        this.latitude = lat;
        this.longitude = lon;
        this.cardinalité = cardi;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public String getCardinalité() {
        return this.cardinalité;
    }

    public void setLatitude(double lat) {
        this.latitude = lat;
    }

    public void setLongitude(double lon) {
        this.longitude = lon;
    }

    public void setCardinalité(String card) {
        this.cardinalité = card;
    }

    @Override
    public String toString() {
        return "Waypoint{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", cardinalité='" + cardinalité + '\'' +
                '}';
    }
}
