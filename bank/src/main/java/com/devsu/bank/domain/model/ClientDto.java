package com.devsu.bank.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientDto {

	private Long clientId;
	private String password;
	private boolean state;
	private PersonDto person;
}
