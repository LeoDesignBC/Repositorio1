package com.partyroom.Retov3.repository.crudRepository;

import org.springframework.data.repository.CrudRepository;

import com.partyroom.Retov3.model.Message;

public interface MessageCrudRepository extends CrudRepository<Message,Integer> {
    
}
