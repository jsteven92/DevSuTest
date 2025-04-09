package com.devsu.usecase;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.devsu.domain.model.Client;
import com.devsu.domain.model.repository.ClientRepositoryPort;
import com.devsu.infraestructure.exceptions.LocalNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ClientInteractorImpl implements IClientInteractor{

	private final ClientRepositoryPort clientRepositoryPort;
	
	@Override
	public List<Client> findClients() {
		return clientRepositoryPort.findClients();
	}

	@Override
	public Optional<Client> findClientsByClientId(Long id) {
		return clientRepositoryPort.findClientsByClientId(id);
	}

	@Override
	public Client saveClient(Client client) {
		return clientRepositoryPort.saveClient(client);
	}

	@Override
	public boolean deleteClient(Long id) {
		return clientRepositoryPort.deleteClient(id);
	}

	@Override
	public Optional<Client> updateClients(Long id, Client client) {
		Client clientAct = clientRepositoryPort.findClientsByClientId(id)
				.orElseThrow(() -> new LocalNotFoundException("Client with Id " + id +" not found"));
	
		if(clientAct.getClientId() == id) {
			client.setClientId(id);
			client.getPerson().setPersonId(clientAct.getPerson().getPersonId());
			return Optional.of(saveClient(client));
		}
		
		return Optional.empty();
	}

	@Override
	public boolean updateState(Long id) {
		Client clientAct = clientRepositoryPort.findClientsByClientId(id)
				.orElseThrow(() -> new LocalNotFoundException("Client with Id " + id +" not found"));
		
		clientAct.setState(!clientAct.isState());
		return clientRepositoryPort.saveClient(clientAct) instanceof Client;
	}


}
