package com.partyroom.Retov3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMessage;
    private String messageText;

    //Muchos mensajes puede tener un salon
    @ManyToOne
    @JoinColumn(name="partyroom")
    @JsonIgnoreProperties({"messages", "client","reservations"})
    private Room partyroom;

    //Muchos mensajes pueden ser de un cliente
    @ManyToOne
    @JoinColumn(name="client")
    @JsonIgnoreProperties({"messages", "reservations","client"})
    private Clients client;

    

    public Room getPartyroom() {
        return partyroom;
    }
    public void setPartyroom(Room partyroom) {
        this.partyroom = partyroom;
    }
    public Clients getClient() {
        return client;
    }
    public void setClient(Clients client) {
        this.client = client;
    }
    public Integer getIdMessage() {
        return idMessage;
    }
    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }
    public String getMessageText() {
        return messageText;
    }
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
    
}
