package com.api.assignment.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.assignment.exception.PersonNotFoundException;
import com.api.assignment.model.LocationJson;
import com.api.assignment.model.PersonJson;
import com.api.assignment.node.Location;
import com.api.assignment.node.Person;
import com.api.assignment.repository.LocationRepository;
import com.api.assignment.repository.PersonRepository;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	LocationRepository locationRepository;

	@Override
	public PersonJson addPerson(PersonJson personJson) {
		Supplier<Person> personSupplier = Person::new;
		Person person = preparePerson.apply(personJson, personSupplier.get());
		person = personRepository.save(person);
		return preparePersonJson.apply(person);
	}

	@Override
	public PersonJson getPersonById(Long id) {
		Optional<Person> optional = personRepository.findById(id);
		if (optional.isPresent()) {
			return preparePersonJson.apply(optional.get());
		} else {
			throw new PersonNotFoundException(id);
		}
	}

	@Override
	public PersonJson updatePerson(PersonJson personJson) {
		Optional<Person> optional = getPersonById.apply(personJson);
		if (optional.isPresent()) {
			Person person = preparePerson.apply(personJson, optional.get());
			person = personRepository.save(person);
			return preparePersonJson.apply(person);
		} else {
			throw new PersonNotFoundException(personJson.getId());
		}
	}

	@Override
	public String deletePerson(Long id) {
		Optional<Person> optional = personRepository.findById(id);
		if (optional.isPresent()) {
			personRepository.deleteById(id);
		} else {
			throw new PersonNotFoundException(id);
		}
		return "Successfully deleted Person: " + id;
	}

	private Function<Person, PersonJson> preparePersonJson = (person) -> {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		Supplier<PersonJson> personSupplier = PersonJson::new;
		Supplier<LocationJson> locationSupplier = LocationJson::new;

		PersonJson personJson = personSupplier.get();
		personJson.setId(person.getId());
		personJson.setName(person.getName());
		personJson.setEmail(person.getEmail());
		personJson.setPhoneNumber(person.getPhoneNumber());
		
		Optional<LocalDateTime> createDateOptional = Optional.ofNullable(person.getCreateDate());
		if(createDateOptional.isPresent()){
			personJson.setCreateDate(myFormatObj.format(createDateOptional.get()));
		}
		
		Optional<LocalDateTime> modifyDateOptional = Optional.ofNullable(person.getModifyDate());
		if(modifyDateOptional.isPresent()){
			personJson.setModifyDate(myFormatObj.format(modifyDateOptional.get()));
		}
		
		LocationJson location = locationSupplier.get();
		location.setId(person.getLocation().getId());
		location.setCity(person.getLocation().getCity());
		personJson.setLocation(location);
		return personJson;
	};

	private BiFunction<PersonJson, Person, Person> preparePerson = (personJson, person) -> {
		String city = personJson.getLocation().getCity();
		Optional<Location> optional = locationRepository.getLocationByCity(city);

		if (optional.isPresent()) {
			person.setLocation(optional.get());
		} else {
			Supplier<Location> locationSupplier = Location::new;
			Location location = locationSupplier.get();
			location.setCity(personJson.getLocation().getCity());
			person.setLocation(location);
		}

		Optional<Long> personId = Optional.ofNullable(personJson.getId());
		if (personId.isPresent()) {
			person.setModifyDate(LocalDateTime.now());
		} else {
			person.setCreateDate(LocalDateTime.now());
		}

		person.setName(personJson.getName());
		person.setPhoneNumber(personJson.getPhoneNumber());
		person.setEmail(personJson.getEmail());

		return person;
	};

	private Function<PersonJson, Optional<Person>> getPersonById = (personJson) -> {
		return personRepository.findById(personJson.getId());
	};
}