package com.devsu.usecase;

import java.util.List;
import java.util.Optional;

import com.devsu.domain.model.Client;

public interface IClientInteractor {
	
	List<Client> findClients();
	Optional<Client> findClientsByClientId(Long id);
	Client saveClient(Client client);
	Optional<Client> updateClients(Long id, Client client);
	boolean deleteClient(Long id);
	boolean updateState(Long id);
}
