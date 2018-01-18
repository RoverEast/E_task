package com.epam;

import java.util.Random;
import java.util.ResourceBundle;

public class Initializer {

    private Building building;
    private ResourceBundle properties = ResourceBundle.getBundle("config");
    private Elevator elevator;
    private Controller controller;
    private Long storiesNumber;
    private Long elevatorCapacity;
    private Long passengersNumber;

    public Initializer() {
        storiesNumber = Long.valueOf(properties.getString("storiesNumber"));
        elevatorCapacity = Long.valueOf(properties.getString("elevatorCapacity"));
        passengersNumber = Long.valueOf(properties.getString("passengersNumber"));
    }

    public void start() throws InterruptedException {
        initBuilding();
        initStories();
        initPassengers();
        initElevator();
        initController();
        controller.moveElevator();
    }

    private void initPassengers() {
        long counter = 0;
        long destinationStory;
        while (counter < passengersNumber) {
            for (Floor floor : building.getFloors()) {
                do {
                    destinationStory = (long) new Random().nextInt(Integer.parseInt(storiesNumber.toString()));
                } while (destinationStory == floor.getFloorNumber() || destinationStory == 0);
                if (counter++ < passengersNumber)
                    floor.getDispatchStoryContainer().add(new Passenger(counter, destinationStory));
            }
        }
    }

    private void initStories() {
        for (int i = 1; i <= storiesNumber; i++) {
            building.getFloors().add(new Floor(i));
        }
    }

    private void initBuilding() {
        building = new Building();
    }

    private void initElevator(){
        elevator = new Elevator(elevatorCapacity);
    }

    private void initController(){
        controller = new Controller(building,elevator);
    }

}
