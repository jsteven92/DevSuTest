package com.devsu.infraestructure.database.mysql.entity;

import java.time.LocalDateTime;

import com.devsu.domain.model.enums.Gender;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "people")
//@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Getter
@Setter
public class PersonEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long personId;

	private String identification;
	private String name;
	private Gender gender;
	private int age;
	private String address;
	private String phone;

	private LocalDateTime dateAdd;
	
	public PersonEntity(Long personId,String identification, String name, Gender gender, int age, String address, String phone) {
		super();
		this.personId = personId;
		this.identification = identification;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.address = address;
		this.phone = phone;
	}

	public void setDateAdd(LocalDateTime dateAdd) {
		this.dateAdd = dateAdd;
	}
	
	
	
	
}
