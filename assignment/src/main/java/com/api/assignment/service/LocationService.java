package com.api.assignment.service;

import com.api.assignment.node.Location;

public interface LocationService {

	Location getLocationByCity(String city);

	Location addLocation(Location location);

}
