package com.partyroom.Retov3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partyroom.Retov3.model.Room;
import com.partyroom.Retov3.repository.RoomRepository;

@Service
public class RoomService {
    
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> obtenerSalones() {
        return roomRepository.obtenerSalones();
    }

    public Optional<Room> roomId(int id){
        return roomRepository.roomId(id);
    }

    public Room saveRoom(Room room) {
      if(room.getId()==null){
            return roomRepository.saveRoom(room);
         }else{
             Optional<Room> roomOptional = roomRepository.roomId(room.getId());
            if(roomOptional.isEmpty()){
                return roomRepository.saveRoom(room);
            }
            else{
              return room;
           }
            
        }
        
    }


    //Metodo update
    public Room updateRoom (Room room){
        if(room.getId()!=null){
            Optional<Room> updatePR=roomRepository.roomId(room.getId());
            if(room.getName()!=null){
                updatePR.get().setName(room.getName());
            }
            if(room.getOwner()!=null){
                updatePR.get().setOwner(room.getOwner());
            }
            if(room.getCapacity()!=null){
                updatePR.get().setCapacity(room.getCapacity());
            }
            if(room.getDescription()!=null){
                updatePR.get().setDescription(room.getDescription());
            }
            if(room.getCategory()!=null){
                updatePR.get().setCategory(room.getCategory());
            }
            if(room.getMessages()!=null){
                updatePR.get().setMessages(room.getMessages());
            }
            if(room.getReservations()!=null){
                updatePR.get().setReservations(room.getReservations());
            }
            roomRepository.saveRoom(updatePR.get());
            return updatePR.get();
    }else{
        return room;
    }
} 

    //Delete
    public boolean deleteRoom(int id){
        Boolean esta=roomId(id).map(room ->{
            roomRepository.borrarRoom(room);
            return true;
        }).orElse(false);
        return esta;
}


}