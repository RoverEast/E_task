package com.epam;

public class TransportationTask implements Runnable {

    private Passenger passenger;
    private volatile boolean complete;


    public TransportationTask(Passenger passenger) {
        this.passenger = passenger;
    }

    public synchronized void run() {
        passenger.setPassengerState(PassengerState.IN_PROGRESS);
        while (!complete) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        passenger.setPassengerState(PassengerState.COMPLETED);
    }

    public synchronized void checkFloor(Long floorNumber) {
        if (floorNumber.equals(passenger.getDestinationStory()))
            complete = true;
        notifyAll();
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public boolean isComplete() {
        return complete;
    }

    @Override
    public String toString() {
        return "TransportationTask{" +
                "passenger=" + passenger +
                '}';
    }

}
