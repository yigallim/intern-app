package com.tarumt.entity.location;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.list.ArrayToLinked;

public class Location {
    private City city;

    public Location(City city) {
        if (city == null) {
            throw new IllegalArgumentException("City cannot be null");
        }
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public State getState() {
        return city.getState();
    }

    public double getLatitude() {
        return city.getLatitude();
    }

    public double getLongitude() {
        return city.getLongitude();
    }

    public void setCity(City city) {
        if (city == null) {
            throw new IllegalArgumentException("City cannot be null");
        }
        this.city = city;
    }

    public static ListInterface<City> getCitiesByState(State state) {
        if (state == null) {
            throw new IllegalArgumentException("State cannot be null");
        }
        ListInterface<City> cities = new DoublyLinkedList<>();
        for (City c : City.values()) {
            if (c.getState() == state) {
                cities.add(c);
            }
        }
        return cities;
    }

    public static ListInterface<State> getAllStates() {
        return ArrayToLinked.asList(State.values());
    }

    public double distanceTo(Location other) {
        if (other == null) {
            throw new IllegalArgumentException("Other location cannot be null");
        }
        double lat1 = Math.toRadians(this.getLatitude());
        double lon1 = Math.toRadians(this.getLongitude());
        double lat2 = Math.toRadians(other.getLatitude());
        double lon2 = Math.toRadians(other.getLongitude());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double earthRadius = 6371;
        return earthRadius * c;
    }

    @Override
    public String toString() {
        return this.city.toString() + ", " + this.city.getState().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return city == location.city;
    }

    @Override
    public int hashCode() {
        return city.hashCode();
    }
}