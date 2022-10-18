package com.partyroom.Retov3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.partyroom.Retov3.model.Clients;
import com.partyroom.Retov3.repository.crudRepository.ClientCrudRepository;


@Repository
public class ClientRepository {
    @Autowired
    private ClientCrudRepository clientCrudRepository;

    public List<Clients> getClients() {
        return (List<Clients>) clientCrudRepository.findAll();
    }

    public Optional<Clients> getClient(int id){
        return clientCrudRepository.findById(id);
    }

    public Clients saveClient(Clients client) {
        return clientCrudRepository.save(client);
    }

    public void deleteClient(Clients client){
        clientCrudRepository.delete(client);
    }
}
