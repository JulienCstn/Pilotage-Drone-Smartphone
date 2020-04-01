package com.ulr.dronemanager;

public class Waypoint {

    private float latitude;
    private float longitude;
    private String cardinalité;

    public Waypoint(float lat, float lon, String cardi) {
        this.latitude = lat;
        this.longitude = lon;
        this.cardinalité = cardi;
    }

    public float getLatitude() {
        return this.latitude;
    }

    public float getLongitude() {
        return this.longitude;
    }

    public String getCardinalité() {
        return this.cardinalité;
    }

    public void setLatitude(float lat) {
        this.latitude = lat;
    }

    public void setLongitude(float lon) {
        this.longitude = lon;
    }

    public void setCardinalité(String card) {
        this.cardinalité = card;
    }

}
