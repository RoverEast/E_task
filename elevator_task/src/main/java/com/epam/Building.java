package com.epam;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Building {

    private Long storiesNumber;
    private List<Floor> floors = new CopyOnWriteArrayList<Floor>();
    private Elevator elevator;

    public Building() {
    }


    public List<Floor> getFloors() {
        return floors;
    }

    public Elevator getElevator() {
        return elevator;
    }
}
