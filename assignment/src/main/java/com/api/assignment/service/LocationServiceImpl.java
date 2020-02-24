package com.api.assignment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.assignment.node.Location;
import com.api.assignment.repository.LocationRepository;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

	@Autowired
	LocationRepository locationRepository;

	/*@Override
	public Person addPerson(Person person) {
		personRepository.save(person);
		return person;
	}*/

	@Override
	public Location getLocationByCity(String city) {
		Optional<Location> optional = locationRepository.getLocationByCity(city);
		/*if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new RuntimeException();
		}*/
		return optional.get();
	}

	@Override
	public Location addLocation(Location location) {
		locationRepository.save(location);
		return location;
	}
	
	/*public List<Person> getAllPerson(){
		return personRepository.
	}*/
}