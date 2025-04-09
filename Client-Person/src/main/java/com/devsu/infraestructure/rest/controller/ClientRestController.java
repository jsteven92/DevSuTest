package com.devsu.infraestructure.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.domain.model.Client;
import com.devsu.infraestructure.exceptions.LocalNotFoundException;
import com.devsu.usecase.IClientInteractor;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clientes")
public class ClientRestController {

	private final IClientInteractor clientInteractor;
	
	@GetMapping("/{clientId}")
	public ResponseEntity<Client> getClientById(@PathVariable Long clientId) {
		return clientInteractor.findClientsByClientId(clientId)
				.map(client -> new ResponseEntity<>(client,HttpStatus.OK))
				.orElseThrow(() -> new LocalNotFoundException("Client not found") );
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Client>> getClientAll() throws LocalNotFoundException{
		return ResponseEntity.ok(clientInteractor.findClients());
	}
	
	@PostMapping
	public ResponseEntity<Client> createClient(@Valid @RequestBody Client client){
		return new ResponseEntity<>(clientInteractor.saveClient(client) , HttpStatus.OK);
	}
	
   @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable Long clientId, @RequestBody Client updatedClient) {
        return clientInteractor.updateClients(clientId, updatedClient)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElseThrow(() -> new LocalNotFoundException("Client not found") );
    }
   
   @DeleteMapping("/{clientId}")
   public ResponseEntity<String> deleteClient(@PathVariable Long clientId){
	   if(clientInteractor.deleteClient(clientId)) {
		   return ResponseEntity.ok("client deleted");
	   }else {
		  throw new LocalNotFoundException("Client not found");
	   }
   }
   
   @PatchMapping("/{clientId}/state")
   public ResponseEntity<String> updateStateClient(@PathVariable Long clientId){
	 
	   if(clientInteractor.updateState(clientId)) {
		   return ResponseEntity.ok("client updated");
	   }else {
		   throw new LocalNotFoundException("Client not found");
	   }
   }
}
