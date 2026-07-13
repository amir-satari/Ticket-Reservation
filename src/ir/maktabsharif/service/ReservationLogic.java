package ir.maktabsharif.service;

import ir.maktabsharif.model.enums.Status;
import ir.maktabsharif.model.models.Event;
import ir.maktabsharif.model.models.Reservation;
import ir.maktabsharif.repository.Impl.EventRepositoryImpl;
import ir.maktabsharif.repository.Impl.ReservationRepositoryImpl;

import java.util.List;

public class ReservationLogic {
    EventRepositoryImpl eventRepository = new EventRepositoryImpl();
    ReservationRepositoryImpl reservationRepository = new ReservationRepositoryImpl();
    //Create Reservation
    //Cancel Reservation
    //Show All Reservations



    public void CreateReservation(Reservation reservation){
        Event event = eventRepository.FindById(reservation.getEvent_id());
        if (event != null
                && event.getStatus().equals(Status.ACTIVE)
                && reservation.getTicket_count() < event.getReserved_count()){
            reservationRepository.Save(reservation);
            event.setReserved_count(event.getReserved_count() - reservation.getTicket_count());
            eventRepository.Update(event);
            System.out.println("Reservation added successfully");
        }else {
            System.out.println("something we wrong");
        }

    }


    public void CancelReservation(Long id){
        Reservation reservation = reservationRepository.FindById(id);
        if (reservation != null){
            reservation.setStatus(Status.CANCELLED);
            Event event = eventRepository.FindById(reservation.getEvent_id());
            if (event != null){
                event.setReserved_count(event.getReserved_count() + reservation.getTicket_count());
                System.out.println("almost done");
            }
        }else {
            System.out.println("something we wrong");
        }

    }

    public void ShowAllReservations(){
        List<Reservation> reservations = reservationRepository.FIndAll();
        reservations.stream().forEach(System.out::println);
    }





}
