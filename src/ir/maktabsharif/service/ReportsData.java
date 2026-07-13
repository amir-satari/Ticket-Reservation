package ir.maktabsharif.service;

import ir.maktabsharif.model.enums.Status;
import ir.maktabsharif.model.models.Event;
import ir.maktabsharif.model.models.Reservation;
import ir.maktabsharif.repository.Impl.EventRepositoryImpl;
import ir.maktabsharif.repository.Impl.ReservationRepositoryImpl;

import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Predicate;

//• Total number of active events.
//• Most expensive event.
//• Average ticket price of all events.
//• List of active reservations.
//• List of fully booked events.

public class ReportsData {

    ReservationRepositoryImpl reservationRepository = new ReservationRepositoryImpl();
    EventRepositoryImpl eventRepository = new EventRepositoryImpl();

    public void TotalNumberOfActiveEvents(){
        List<Event> events = eventRepository.FIndAll();
        events.stream().filter(e -> e.getStatus().equals(Status.ACTIVE)).forEach(System.out::println);
    }


    public void MostExpensiveEvent(){
        List<Event> events = eventRepository.FIndAll();
        events.stream().filter(e -> e.getTicket_price() > 200).forEach(System.out::println);
    }

    public void AverageTicketPriceOfAllEvents(){
        List<Event> events = eventRepository.FIndAll();
        OptionalDouble a = events.stream().mapToDouble(e -> e.getTicket_price()).average();
        System.out.println(a);
    }

    public List<Reservation> ListOfActiveReservations(){
        List<Reservation> reservations = reservationRepository.FIndAll();
        List<Reservation> reservations1 = reservations.stream().filter(r -> r.getStatus().equals(Status.ACTIVE)).toList();
        return reservations1;
    }

    public List<Event> ListOfFullyBookedEvents(){
        List<Event> events = eventRepository.FIndAll();
        Predicate<Event> eventPredicate = (e) -> e.getReserved_count() == e.getCapacity();

        List<Event> events1 = events.stream().filter(eventPredicate).toList();
        return events1;
    }

}
