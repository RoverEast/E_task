package com.epam;

public class Passenger {

    private Long passengerID;
    private Long destinationStory;
    private PassengerState passengerState = PassengerState.NOT_STARTED;

    public Passenger(Long passengerID, Long destinationStory) {
        this.passengerID = passengerID;
        this.destinationStory = destinationStory;
    }

    public Long getPassengerID() {
        return passengerID;
    }

    public Long getDestinationStory() {
        return destinationStory;
    }

    public void setPassengerState(PassengerState passengerState) {
        this.passengerState = passengerState;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerID=" + passengerID +
                ", destinationStory=" + destinationStory +
                ", passengerState=" + passengerState +
                '}';
    }
}
