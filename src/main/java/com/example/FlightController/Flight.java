package com.example.FlightController;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {
    private String reference;
    private LocalDateTime departureDate;
    private String origin;
    private String destination;

    public Flight(String reference, LocalDateTime departureDate, String origin, String destination) {
        this.reference=reference;
        this.departureDate=departureDate;
        this.origin = origin;
        this.destination=destination;
    }

    public String getReference() {
        return reference;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(reference, flight.reference) && Objects.equals(departureDate, flight.departureDate) && Objects.equals(origin, flight.origin) && Objects.equals(destination, flight.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, departureDate, origin, destination);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "reference='" + reference + '\'' +
                ", departureDate=" + departureDate +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
