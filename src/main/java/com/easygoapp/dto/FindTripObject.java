package com.easygoapp.dto;

import com.easygoapp.domain.PassengerNodePoint;
import com.easygoapp.domain.Trip;

import java.util.List;

/**
 * Created by Станислав on 06.03.2015.
 */
public class FindTripObject {

    String startTime;
    String endTime;
    List<PassengerNodePoint> passengerNodePoints;
    List<Trip> trips;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<PassengerNodePoint> getPassengerNodePoints() {
        return passengerNodePoints;
    }

    public void setPassengerNodePoints(List<PassengerNodePoint> passengerNodePoints) {
        this.passengerNodePoints = passengerNodePoints;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
