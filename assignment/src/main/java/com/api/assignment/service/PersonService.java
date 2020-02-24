package com.api.assignment.service;

import com.api.assignment.model.PersonJson;

public interface PersonService {

	PersonJson getPersonById(Long id);

	PersonJson addPerson(PersonJson personJson);

	PersonJson updatePerson(PersonJson personJson);

	String deletePerson(Long id);

}
