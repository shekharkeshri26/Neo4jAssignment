package com.api.assignment.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonJson {

	private Long id;

	@NotNull(message="Name can't be null")
	@Size(min=3,max=20, message="Name must be greater than 2 characters and less than 20 characters")
	private String name;
	
	@NotNull(message="Email can't be null")
	@Email(message = "Email should be valid")
	private String email;
	
	@NotNull(message="Phone Number can't be null")
	@Size(min=6,max=12, message="Phone Number must be greater than 6 characters and less than 12 characters")
	private String phoneNumber;
	
	private String createDate;
	private String modifyDate;
	private LocationJson location;
}
