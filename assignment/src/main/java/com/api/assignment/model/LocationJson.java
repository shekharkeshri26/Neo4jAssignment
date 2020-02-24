package com.api.assignment.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationJson {

	private Long id;

	@NotNull(message = "City Name can't be null")
	@Size(min = 6, max = 12, message = "City Name must be greater than 3 characters and less than 12 characters")
	private String city;
}
