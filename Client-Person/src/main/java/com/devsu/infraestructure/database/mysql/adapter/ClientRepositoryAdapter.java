package com.devsu.infraestructure.database.mysql.adapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.devsu.domain.model.Client;
import com.devsu.domain.model.repository.ClientRepositoryPort;
import com.devsu.infraestructure.database.mysql.entity.ClientEntity;
import com.devsu.infraestructure.database.mysql.repository.ClientJpaRepository;
import com.devsu.infraestructure.exceptions.LocalNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ClientRepositoryAdapter implements ClientRepositoryPort {
	
	private final ClientJpaRepository clientJpaRepository;
	
	@Override
	public List<Client> findClients() {
		
		List<ClientEntity> clientList = clientJpaRepository.findAll();
		
		if(clientList.size() == 0) {
			throw new LocalNotFoundException("Client List Empty");
		}
		
		return clientList
				.stream()
				.map(ClientEntity::toDomainModel)
				.collect(Collectors.toList());
		
	}

	@Override
	public Optional<Client> findClientsByClientId(Long id) {
		return clientJpaRepository.findById(id)
				.map(client -> client.toDomainModel());
	}

	@Override
	public Client saveClient(Client client) {
		 ClientEntity clientEntity =  ClientEntity.fromDomainModel(client);
		 clientEntity.getPerson().setDateAdd(LocalDateTime.now());
		 return clientJpaRepository.save(clientEntity).toDomainModel();
	}

	@Override
	public boolean deleteClient(Long id) {
		clientJpaRepository.deleteById(id);
		return !clientJpaRepository.existsById(id);
	}

}
