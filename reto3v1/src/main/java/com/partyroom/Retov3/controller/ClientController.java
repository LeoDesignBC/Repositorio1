package com.partyroom.Retov3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.partyroom.Retov3.model.Clients;
import com.partyroom.Retov3.service.ClientService;

@RestController
@RequestMapping("/api/Client")
@CrossOrigin(origins="*", methods ={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public List<Clients> getClients(){
        return clientService.getClients();
    }

    @GetMapping("/{id}")
    public Optional<Clients> getClient(@PathVariable("id")int id){
        return clientService.getClient(id);
    } 

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Clients saveClient(@RequestBody Clients client){
        return clientService.saveClient(client);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Clients updateClient(@RequestBody Clients client){
        return clientService.updateClient(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)  //no content porque el endpoint es para recibir información 
    public boolean deleteClient(@PathVariable("id")int id){
        return clientService.deleteClient(id);
    }
}
