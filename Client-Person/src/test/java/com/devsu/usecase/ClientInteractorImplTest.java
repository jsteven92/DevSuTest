package com.devsu.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsu.domain.model.Client;
import com.devsu.domain.model.Person;
import com.devsu.domain.model.enums.Gender;
import com.devsu.domain.model.repository.ClientRepositoryPort;
import com.devsu.infraestructure.exceptions.LocalNotFoundException;

class ClientInteractorImplTest {
	
	private IClientInteractor clientInteractorImpl;
	private ClientRepositoryPort clientRepositoryPort;
	
	@BeforeEach
	void setUp() {
		clientRepositoryPort = mock(ClientRepositoryPort.class);
		clientInteractorImpl = new ClientInteractorImpl(clientRepositoryPort);
	}
	
	@Test
	void TestUpdateState() {
		Client client = createClient();
		when(clientRepositoryPort.findClientsByClientId(any(Long.class) )).thenReturn(Optional.of(client));
		when(clientRepositoryPort.saveClient(any(Client.class))).thenReturn(client);
		
        boolean result = clientInteractorImpl.updateState(client.getClientId());

        assertFalse(client.isState());
        assertTrue(result);

        verify(clientRepositoryPort).findClientsByClientId(client.getClientId());
        verify(clientRepositoryPort).saveClient(client);
		
	}
	
	@Test
    void testUpdateState_ClientNotFound_ShouldThrowException() {
        // Given
        Long clientId = 999L;
        when(clientRepositoryPort.findClientsByClientId(clientId)).thenReturn(Optional.empty());

        // Then
        LocalNotFoundException thrown = assertThrows(
            LocalNotFoundException.class,
            () -> clientInteractorImpl.updateState(clientId)
        );

        assertEquals("Client with Id 999 not found", thrown.getMessage());

        verify(clientRepositoryPort).findClientsByClientId(clientId);
        verify(clientRepositoryPort, never()).saveClient(any());
    }
	
	public Client createClient() {
		return new Client(1L, "1234", true, new Person(1L, "123456", "name test", Gender.MASCULINO, 20, "av 25", "1234567899"));
	}

}
