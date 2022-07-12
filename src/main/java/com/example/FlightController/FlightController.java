package com.example.FlightController;

import java.util.*;

public class FlightController {

    private Map<String,Flight> flights ;

    public FlightController() {
        this.flights = new HashMap<String, Flight>();
    }

    public void addFlight(Flight flight) {
        Flight existingFlight = flights.get(flight.getReference());
        if(existingFlight != null) {
            throw new DuplicateFlightException();
        }
        flights.put(flight.getReference(),flight);
    }

    public Flight findFlightByReference(String reference) {
        Flight flight= flights.get(reference);
        if(flight == null){
            throw new FlightNotFoundException();
        }
        return flight;
    }


    public List<Flight> findFlightByOrigin(String origin) {
        List<Flight> flightsByOrigin = new ArrayList<>();
        Collection<Flight> flightsValues = flights.values();

        for(Flight f: flightsValues){
            if (f.getOrigin().equals(origin)) {
                flightsByOrigin.add(f);
            }
        }
        return flightsByOrigin;
    }

    public void deleteFlight(String reference) {
        if(flights.remove(reference) == null){
            throw new FlightNotFoundException();
        }
    }
}
