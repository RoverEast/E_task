package com.epam;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Elevator {

    private final static Logger log = Logger.getLogger(Elevator.class);

    private Long capacity;
    private long floorNumber = 1;
    private List<TransportationTask> elevatorContainer = new CopyOnWriteArrayList<TransportationTask>();


    public Elevator(Long capacity) {
        this.capacity = capacity;
    }

    public Long getFloorNumber() {
        return floorNumber;
    }

    public void startTransportation() {
        log.info("STARTING_TRANSPORTATION");
    }

    public void completeTransportation() {
        log.info("COMPLETION_TRANSPORTATION");
    }

    public void setFloorNumber(long floorNumber) {
        this.floorNumber = floorNumber;
    }

    public List<TransportationTask> getElevatorContainer() {
        return elevatorContainer;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void move(Long from, Long to) {
        log.info("MOVING_ELEVATOR (from story" + from + " to story" + to + ")");
        floorNumber = to;
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "capacity=" + capacity +
                ", floorNumber=" + floorNumber +
                ", elevatorContainer=" + elevatorContainer +
                '}';
    }
}
