package com.partyroom.Retov3.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.partyroom.Retov3.model.Clients;
import com.partyroom.Retov3.model.Reservation;
import com.partyroom.Retov3.model.custom.CountClient;
import com.partyroom.Retov3.repository.crudRepository.ReservationCrudRepository;

@Repository
public class ReservationRepository {
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;

    public List<Reservation> getReservations() {
        return (List<Reservation>) reservationCrudRepository.findAll();
    }

    public Optional<Reservation> getReservation(int id){
        return reservationCrudRepository.findById(id);
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationCrudRepository.save(reservation);
    }

    public void deleteReservation(Reservation reservation){
        reservationCrudRepository.delete(reservation);
    }

    /* Reporte en un rango de fechas */
    public List<Reservation> getReservationPeriod(Date a, Date b){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(a,b);
    }

    /*Reporte por Status completed or cancelled */
    public List<Reservation> getReservationByStatus(String status){
        return reservationCrudRepository.findAllByStatus(status);
    }

    /*Para el top de clientes */
    public List<CountClient> getTopClients(){
        List<CountClient> cuenta=new ArrayList<>(); 

        List<Object[]> report =reservationCrudRepository.countReservationByClient();
        for(int i=0;i<report.size();i++){

            Clients cl=(Clients) report.get(i)[0];
            Long cantidad=(Long)report.get(i)[1];
            CountClient cc=new CountClient(cantidad, cl);
            cuenta.add(cc);

            /*cuenta.add(new CountClient((Long) report.get(i)[1],(Clients)report.get(i)[0])); */
        }

        return cuenta;
    }
}
