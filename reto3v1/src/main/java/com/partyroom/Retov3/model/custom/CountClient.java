package com.partyroom.Retov3.model.custom;

import com.partyroom.Retov3.model.Clients;

public class CountClient {
    
    private Long total;  // Cantidad total del grupo
    private Clients client; //El nombre del grupo
    
    public CountClient(Long total, Clients client) {
        this.total = total;
        this.client = client;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }
    
    
    
   
    
}
