package com.epam;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Floor {

    private long floorNumber;
    private List<Passenger> arrivalStoryContainer = new CopyOnWriteArrayList<Passenger>();
    private List<Passenger> dispatchStoryContainer = new CopyOnWriteArrayList<Passenger>();

    public Floor(long floorNumber) {
        this.floorNumber = floorNumber;
    }

    public long getFloorNumber() {
        return floorNumber;
    }

    public List<Passenger> getArrivalStoryContainer() {
        return arrivalStoryContainer;
    }

    public List<Passenger> getDispatchStoryContainer() {
        return dispatchStoryContainer;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "floorNumber=" + floorNumber +
                ", arrivalStoryContainer=" + arrivalStoryContainer +
                ", dispatchStoryContainer=" + dispatchStoryContainer +
                '}';
    }
}
