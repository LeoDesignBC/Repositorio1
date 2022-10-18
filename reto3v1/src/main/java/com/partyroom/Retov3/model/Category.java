package com.partyroom.Retov3.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    //una categoria puede tener muchos salones por eso se debe hacer una lista
    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties ({"category"})
    private List<Room> partyrooms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Room> getPartyrooms() {
        return partyrooms;
    }

    public void setPartyrooms(List<Room> partyrooms) {
        this.partyrooms = partyrooms;
    }


    

}
