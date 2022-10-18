package com.partyroom.Retov3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.partyroom.Retov3.model.Room;
import com.partyroom.Retov3.repository.crudRepository.RoomCrudRepository;

@Repository
public class RoomRepository {

    @Autowired
    private RoomCrudRepository roomCrudRepository;

    public List<Room> obtenerSalones() {
        return (List<Room>) roomCrudRepository.findAll();
    }

    public Optional <Room> roomId(int id){
        return roomCrudRepository.findById(id);
    }

    public Room saveRoom(Room room) {
        return roomCrudRepository.save(room);
    }

    public void borrarRoom(Room room){
        roomCrudRepository.delete(room);
    }
}
