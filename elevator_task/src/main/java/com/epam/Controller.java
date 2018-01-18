package com.epam;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Controller {

    private final static Logger log = Logger.getLogger(Controller.class);

    private Building building;
    private Elevator elevator;
    private boolean moveUp = true;

    public Controller(Building building, Elevator elevator) {
        this.building = building;
        this.elevator = elevator;
    }

    public Building getBuilding() {
        return building;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void moveElevator() throws InterruptedException {
        elevator.startTransportation();
        while (elevator.getElevatorContainer().size() != 0 || !checkContainers()) {
            loadPassengers();
            move();
            checkPassengerTask();
            Thread.sleep(200);
        }

        elevator.completeTransportation();
    }

    private void checkDirection() {
        if (elevator.getFloorNumber() == 1) {
            moveUp = true;
        } else if (building.getFloors().size() == elevator.getFloorNumber()) {
            moveUp = false;
        }
    }

    private void checkPassengerTask() {
        for (TransportationTask task : elevator.getElevatorContainer()) {
            task.checkFloor(elevator.getFloorNumber());
            if (task.isComplete()) {
                Floor floor = getFloorByNumber(elevator.getFloorNumber());
                floor.getArrivalStoryContainer().add(task.getPassenger());
                elevator.getElevatorContainer().remove(task);
                log.info("DEBOADING_OF_PASSENGER ID = "+ task.getPassenger().getPassengerID()+" ON_STORY NUMBER = "+floor.getFloorNumber());
            }
        }
    }

    private void loadPassengers() throws InterruptedException {
        Floor floor = getFloorByNumber(elevator.getFloorNumber());
        List<Passenger> toLoad = getPassengerToLoad(floor);
        for (Passenger passenger : toLoad) {
            if (elevator.getElevatorContainer().size() <= elevator.getCapacity() || toLoad.size() > 0) {
                TransportationTask task = new TransportationTask(passenger);
                elevator.getElevatorContainer().add(task);
                Thread t = new Thread(task);
                t.setPriority(Thread.MAX_PRIORITY);
                t.start();
                floor.getDispatchStoryContainer().remove(passenger);
                log.info("BOADING_OF_PASSENGER ID = "+ task.getPassenger().getPassengerID()+" ON_STORY NUMBER = "+floor.getFloorNumber());
            }
        }
    }

    private List<Passenger> getPassengerToLoad(Floor floor) {
        List<Passenger> passengerList = new CopyOnWriteArrayList<Passenger>();
        if (floor.getFloorNumber() == 1) {
            passengerList = floor.getDispatchStoryContainer().stream().filter(x -> x.getDestinationStory() > elevator.getFloorNumber()).collect(Collectors.toList());
        } else if (floor.getFloorNumber() == building.getFloors().size()) {
            passengerList = floor.getDispatchStoryContainer().stream().filter(x -> x.getDestinationStory() < elevator.getFloorNumber()).collect(Collectors.toList());
        } else if (moveUp) {
            passengerList = floor.getDispatchStoryContainer().stream().filter(x -> x.getDestinationStory() > elevator.getFloorNumber()).collect(Collectors.toList());
        } else if (moveUp == false) {
            passengerList = floor.getDispatchStoryContainer().stream().filter(x -> x.getDestinationStory() < elevator.getFloorNumber()).collect(Collectors.toList());
        }

        return passengerList;
    }

    private Floor getFloorByNumber(Long floorNumber) {
        return building.getFloors().stream().filter(x -> x.getFloorNumber() == floorNumber).findFirst().get();
    }

    private void move() {
        checkDirection();
        if (moveUp) {
            elevator.move(elevator.getFloorNumber(), elevator.getFloorNumber() + 1);
        } else {
            elevator.move(elevator.getFloorNumber(), elevator.getFloorNumber() - 1);
        }
    }

    private boolean checkContainers() {
        for (Floor floor : building.getFloors()) {
            if (floor.getDispatchStoryContainer().size() != 0)
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controller{" +
                "elevator=" + elevator +
                '}';
    }
}
