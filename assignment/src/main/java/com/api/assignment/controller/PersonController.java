package com.api.assignment.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.assignment.model.PersonJson;
import com.api.assignment.service.PersonService;

@RestController
@RequestMapping("/person/v1")
public class PersonController {

	@Autowired
	PersonService personService;
	
	@GetMapping("/person/{id}")
	public ResponseEntity<PersonJson> getPersonById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(personService.getPersonById(id));
	}

	@PostMapping("/person")
	public ResponseEntity<PersonJson> addPerson(@Valid @RequestBody PersonJson personJson) {
		return ResponseEntity.status(HttpStatus.OK).body(personService.addPerson(personJson));
	}
	
	@PutMapping("/person")
	public ResponseEntity<PersonJson> updatePerson(@Valid @RequestBody PersonJson personJson) {
		return ResponseEntity.status(HttpStatus.OK).body(personService.updatePerson(personJson));
	}
	
	@DeleteMapping("/person/{id}")
	public ResponseEntity<String> deletePerson(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(personService.deletePerson(id));
	}
}