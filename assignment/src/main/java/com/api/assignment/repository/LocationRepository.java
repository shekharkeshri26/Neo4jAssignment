package com.api.assignment.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.api.assignment.node.Location;

public interface LocationRepository extends Neo4jRepository<Location,Long> {

	Optional<Location> getLocationByCity(String city);
}
