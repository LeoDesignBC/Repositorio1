package com.partyroom.Retov3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partyroom.Retov3.model.Clients;
import com.partyroom.Retov3.repository.ClientRepository;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;

    public List<Clients> getClients() {
        return clientRepository.getClients();
    }

    public Optional<Clients> getClient(int clientId){
        return clientRepository.getClient(clientId);
    }


    public Clients saveClient(Clients client) {
        if (client.getIdClient()==null){
            return clientRepository.saveClient(client);
        }else {
            Optional<Clients> newClient = clientRepository.getClient(client.getIdClient());
            if (newClient.isEmpty()){
                return clientRepository.saveClient(client);
            }else {
                return client;
            }    
        }

    }

    public Clients updateClient(Clients client){
        if(client.getIdClient() !=null){
            Optional<Clients> itemsAct=clientRepository.getClient(client.getIdClient());
            if(client.getEmail()!=null){
                itemsAct.get().setEmail(client.getEmail());
            }
            if(client.getPassword() !=null){
                itemsAct.get().setPassword(client.getPassword());
            }
            if(client.getName()!=null){
                itemsAct.get().setName(client.getName());
            }
            if(client.getAge()!=null){
                itemsAct.get().setAge(client.getAge());
            }
            if(client.getMessages()!=null){
                itemsAct.get().setMessages(client.getMessages());
            }
            if(client.getReservations()!=null){
                itemsAct.get().setReservations(client.getReservations());
            }
            clientRepository.saveClient(itemsAct.get());
            return itemsAct.get();
        }else{
            return client;
        }
    }

    public boolean deleteClient(int clientId){
        Boolean d= getClient(clientId).map(client->{
            clientRepository.deleteClient(client);
            return true;
        }).orElse(false);
        return d;
    }
}
