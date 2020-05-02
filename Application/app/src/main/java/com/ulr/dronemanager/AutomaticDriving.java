package com.ulr.dronemanager;
import java.util.*;

import java.util.Collection;
public class AutomaticDriving {
    private DroneNetwork myDroneNetwork;
    private Path myPath;

    public AutomaticDriving(){

    }
    public AutomaticDriving(DroneNetwork myDroneNetwork, Path myPath){
        this.myDroneNetwork = myDroneNetwork;
        this.myPath = myPath;
    }

    public String getInformation(){
        return myDroneNetwork.getEnvironemental();
    }

    public void goBackToFirstPoint(){
        //TODO implementer la fonction
        //Reverse our path
        //Collections.reverse(myPath.getWaypoints());
        //Send new path to the drone
        //myDroneNetwork.sendPath(myPath);

    }

    public DroneNetwork getMyDroneNetwork() {
        return myDroneNetwork;
    }

    public Path getMyPath() {
        return myPath;
    }

    public void setMyDroneNetwork(DroneNetwork myDroneNetwork) {
        this.myDroneNetwork = myDroneNetwork;
    }

    public void setMyPath(Path myPath) {
        this.myPath = myPath;
    }

    @Override
    public String toString() {
        return "AutomaticDrivingActivity{" + "myDroneNetwork=" + myDroneNetwork + ", myPath=" + myPath +'}';
    }




}
