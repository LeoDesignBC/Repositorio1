package com.partyroom.Retov3.repository.crudRepository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.partyroom.Retov3.model.Reservation;

//Para enconcontrar la lista de reservas realizadas en un periodo de tiempo
public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer>{

    
    //startDate after And StartDate before: compara StarDate sea posterior al primer argumento y anterior al segundo
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne,Date dateTwo);

    /*Reporte por status */
    public List<Reservation> findAllByStatus(String status);

    /**esta necesita ser interpretada */
    @Query("SELECT cuenta.client, COUNT(cuenta.client) FROM Reservation AS cuenta group by cuenta.client order by COUNT(cuenta.client) desc")
    public List<Object[]> countReservationByClient();

    /**para usar las consultas anteriores: llamarlos, invocarlos y arreglarlos */
}
