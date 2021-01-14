package com.works.soworks.soworksapi.controller;

import com.works.soworks.soworksapi.domain.model.Client;
import com.works.soworks.soworksapi.domain.repository.ClientRepository;
import com.works.soworks.soworksapi.domain.service.CRUDClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CRUDClientService clientService;

    @GetMapping
    public List<Client> list(){
        return clientRepository.findAll();
    }

    // @PathVariable makes the bind
    @GetMapping("/{clientId}")
    public ResponseEntity<Client> searchById(@PathVariable Long clientId){
        Optional<Client> client = clientRepository.findById(clientId);
        if(client.isPresent())
            return ResponseEntity.ok(client.get());

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client insertClient(@Valid @RequestBody Client client){
        return clientService.save(client);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable Long clientId , @Valid @RequestBody Client client){
        if(!clientRepository.existsById(clientId))
            return ResponseEntity.notFound().build();
        client.setId(clientId);
        client = clientService.save(client);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId){
        if(!clientRepository.existsById(clientId))
            return ResponseEntity.notFound().build();
        clientService.delete(clientId);
        return ResponseEntity.ok().build();
    }
}
