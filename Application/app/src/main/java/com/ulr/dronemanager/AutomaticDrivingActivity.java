package com.ulr.dronemanager;
import java.util.*;

import java.util.Collection;

public class AutomaticDrivingActivity {
    private DroneNetwork myDroneNetwork;
    private Path myPath;

    public AutomaticDrivingActivity(DroneNetwork myDroneNetwork, Path myPath)
        this.myDroneNetwork = myDroneNetwork;
        this.myPath = myPath;
    }

    public String getInformation(){
        return myDroneNetwork.getEnvironemental();
    }

    private boolean goBackToFirstPoint(){
        //Reverse our path
        Collections.reverse(myPath.getWaypoints());
        //Send new path to the drone
        myDroneNetwork.sendPath(mypath);
        return true;
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
