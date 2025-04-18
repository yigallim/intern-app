/**
 * @author Lim Yuet Yang
 */

package com.tarumt.boundary;

import com.tarumt.entity.location.Location;
import com.tarumt.entity.location.State;
import com.tarumt.entity.location.City;
import com.tarumt.utility.common.Input;

import com.tarumt.adt.list.ListInterface;

public class LocationUI {
    private final Input input;

    public LocationUI(Input input) {
        this.input = input;
    }

    public Location getLocation() {
        System.out.println("| Location ==>");
        State state = input.getEnum("| Select a State => ", State.class);
        if (state == null) return null;
        ListInterface<City> cityList = Location.getCitiesByState(state);
        City city = input.getObjectFromList("|\n| Select a City in " + state + " => ", cityList);
        if (city == null) return null;
        return new Location(city);
    }
}