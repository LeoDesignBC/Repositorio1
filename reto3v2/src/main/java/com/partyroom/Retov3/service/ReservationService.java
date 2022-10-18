package com.partyroom.Retov3.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partyroom.Retov3.model.Reservation;
import com.partyroom.Retov3.model.custom.CountClient;
import com.partyroom.Retov3.model.custom.CountPerStatus;
import com.partyroom.Retov3.repository.ReservationRepository;

@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getReservations() {
        return reservationRepository.getReservations();
    }

    public Optional<Reservation> getReservation(int reservationId){
        return reservationRepository.getReservation(reservationId);
    }


    public Reservation saveReservation(Reservation reservation) {
        if(reservation.getIdReservation()==null){
            return reservationRepository.saveReservation(reservation);
       }else{
            Optional<Reservation> a=reservationRepository.getReservation(reservation.getIdReservation());
            if(a.isEmpty()){
                return reservationRepository.saveReservation(reservation);
            }else{
                return reservation;
            }
       }   
    }

    public Reservation updateReservation(Reservation reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservation> updateR=reservationRepository.getReservation(reservation.getIdReservation());
            if(reservation.getStartDate()!=null){
                updateR.get().setStartDate(reservation.getStartDate());
            }
            if(reservation.getDevolutionDate()!=null){
                updateR.get().setDevolutionDate(reservation.getDevolutionDate());
            }
            if(reservation.getStatus()!=null){
                updateR.get().setStatus(reservation.getStatus());
            }
            if(reservation.getPartyroom()!=null){
                updateR.get().setPartyroom(reservation.getPartyroom());
            }
            if(reservation.getClient()!=null){
                updateR.get().setClient(reservation.getClient());
            }
            if(reservation.getScore()!=null){
                updateR.get().setScore(reservation.getScore());
            }
            reservationRepository.saveReservation(updateR.get());
            return updateR.get();
            }else{
                return reservation;
            }
        }

    //Delete Reservation
    public Boolean deleteReservation(int reservationId){
        Boolean deleteR=getReservation(reservationId).map(reservation->{
            reservationRepository.deleteReservation(reservation);
            return true;
        }).orElse(false);
        return deleteR;
    }

    //Reporte reservaciones en un rango de tiempo
    public List<Reservation> getReservationPeriod(String dateOne, String dateTwo) {
        SimpleDateFormat parser=new SimpleDateFormat("yyy-MM-dd");
        Date a=new Date();
        Date b=new Date();
        try{
            a=parser.parse(dateOne);
            b=parser.parse(dateTwo);
        }catch(ParseException e){
            e.printStackTrace();
        }
        if(a.before(b)){
            return reservationRepository.getReservationPeriod(a,b);
        }else{
            return new ArrayList<>();
        }
        
    }

    /** Reporte por status de completado o cancelado */
    public CountPerStatus getStatusReport(){
        List<Reservation> completed=reservationRepository.getReservationByStatus("completed");
        List<Reservation> cancelled=reservationRepository.getReservationByStatus("cancelled");

        CountPerStatus statusCC=new CountPerStatus(completed.size(),cancelled.size());
        return statusCC;
    }

    /*Reporte Top clientes */
    public List<CountClient> getTopClient(){
        return reservationRepository.getTopClients();
    }
}
