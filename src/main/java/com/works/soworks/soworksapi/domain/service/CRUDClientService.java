package com.works.soworks.soworksapi.domain.service;

import com.works.soworks.soworksapi.domain.exception.ClientException;
import com.works.soworks.soworksapi.domain.model.Client;
import com.works.soworks.soworksapi.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CRUDClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client save(Client client){
        Client clientExists = clientRepository.findByEmail(client.getEmail());
        if(clientExists != null && !clientExists.equals(client))
            throw new ClientException("Email is already taken");
        else
            return clientRepository.save(client);
    }

    public void delete(Long clientId){
        clientRepository.deleteById(clientId);
    }
}
