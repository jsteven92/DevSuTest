package com.devsu.infraestructure.database.mysql.entity;

import com.devsu.domain.model.Client;
import com.devsu.domain.model.Person;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
//@PrimaryKeyJoinColumn(name = "personId")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long clientId;
	 
	 @NotBlank(message = "Password field cannot be empty ")
	 private String password;
	 
	 private boolean state;
	 
	 @OneToOne(optional = false, cascade = CascadeType.ALL)
	 @JoinColumn(name = "personId", unique = true, nullable = false)
	 private PersonEntity person;
	 
	 public Client toDomainModel() {
		 return new Client(
				 clientId,
				 password, 
				 state , 
				 new Person(person.getPersonId(),
						 person.getIdentification(), 
						 person.getName(), 
						 person.getGender(), 
						 person.getAge(), 
						 person.getAddress(), 
						 person.getPhone()));
	 }
	 
	 public static ClientEntity fromDomainModel(Client client) {
		 PersonEntity personEntity = new PersonEntity(
				 client.getPerson().getPersonId(),
				 client.getPerson().getIdentification(), 
				 client.getPerson().getName(), 
				 client.getPerson().getGender(), 
				 client.getPerson().getAge(), 
				 client.getPerson().getAddress(),
				 client.getPerson().getPhone());
		 
		 return new ClientEntity(
				 client.getClientId(),
				 client.getPassword(),
				 client.isState()
				 ,personEntity
				 );
	 }
}
