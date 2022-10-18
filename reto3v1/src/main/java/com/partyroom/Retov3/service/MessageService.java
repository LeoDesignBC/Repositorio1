package com.partyroom.Retov3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partyroom.Retov3.model.Message;
import com.partyroom.Retov3.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getMessages() {
        return messageRepository.getMessages();
    }

    public Optional<Message> getMessage(int messageId){
        return messageRepository.getMessage(messageId);
    }


    public Message saveMessage(Message message) {
        if (message.getIdMessage()==null){
            return messageRepository.saveMessage(message);
        }else{
            Optional<Message> recMessage =messageRepository.getMessage(message.getIdMessage());
            if (recMessage.isEmpty()){
                return messageRepository.saveMessage(message);
            }else{
                return message;
            }
        }
    }

    public Message updateMessage(Message message){
        if (message.getIdMessage() !=null){
            Optional<Message> update=messageRepository.getMessage(message.getIdMessage());
            if(message.getMessageText() !=null){
                update.get().setMessageText(message.getMessageText());
            }
            if(message.getPartyroom() !=null){
                update.get().setPartyroom(message.getPartyroom());
            }
            if(message.getClient() !=null){
                update.get().setClient(message.getClient());
            }
            messageRepository.saveMessage(update.get());
            return update.get();
        }else{
            return message;
        }
    }

   public boolean deleteMessage(int messageId){
        Boolean deleted= getMessage(messageId).map(message->{
            messageRepository.deleteMessage(message);
            return true;
        }).orElse(false);
        return deleted;
    }
}
