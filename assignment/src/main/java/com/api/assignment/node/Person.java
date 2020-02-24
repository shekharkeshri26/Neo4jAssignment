package com.api.assignment.node;

import java.time.LocalDateTime;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person{

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String email;
	private String phoneNumber;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private Location location;
}
