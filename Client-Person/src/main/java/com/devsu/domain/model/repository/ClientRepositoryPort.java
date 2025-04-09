package com.devsu.domain.model.repository;

import java.util.List;
import java.util.Optional;

import com.devsu.domain.model.Client;

public interface ClientRepositoryPort {
	List<Client> findClients();
	Optional<Client> findClientsByClientId(Long id);
	Client saveClient(Client client);
	boolean deleteClient(Long id);

}
