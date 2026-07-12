package ir.maktabsharif;

import ir.maktabsharif.model.enums.Status;
import ir.maktabsharif.model.models.Event;
import ir.maktabsharif.model.models.Reservation;
import ir.maktabsharif.service.EventLogic;
import ir.maktabsharif.service.ReportsData;
import ir.maktabsharif.service.ReservationLogic;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    //1. Create Event
    //2. Show All Events
    //3. Update Event
    //4. Cancel Event
    //5. Create Reservation
    //6. Cancel Reservation
    //7. Show All Reservations
    //8. Reports
    //9. Exit
    public static void main(String[] args) {
        ReservationLogic reservationLogic = new ReservationLogic();
        EventLogic eventLogic = new EventLogic();
        ReportsData reportsData = new ReportsData();


        //1. Create Event

        Event event1 = new Event(
                "Java Bootcamp",
                "Tehran",
                100,
                45,
                350000,
                Status.ACTIVE
        );

        Event event2 = new Event(
                "Spring Workshop",
                "Mashhad",
                80,
                80,
                500000,
                Status.ACTIVE
        );

        Event event3 = new Event(
                "Database Conference",
                "Isfahan",
                200,
                120,
                750000,
                Status.ACTIVE
        );

        Event event4 = new Event(
                "AI Seminar",
                "Shiraz",
                150,
                0,
                900000,
                Status.CANCELLED
        );

        Event event5 = new Event(
                "Kotlin Meetup",
                "Tabriz",
                60,
                25,
                250000,
                Status.ACTIVE
        );

        eventLogic.CreateEvent(event1);
        eventLogic.CreateEvent(event2);
        eventLogic.CreateEvent(event3);
        eventLogic.CreateEvent(event4);
        eventLogic.CreateEvent(event5);

        //======================================================

        //2. Show All Events

        eventLogic.ShowAllEvents();


        //======================================================


        //3. Update Event
        //4. Cancel Event

        event2.setStatus(Status.CANCELLED);
        event2.setLocation("new york");
        eventLogic.UpdateEvent(event2);

        //======================================================

        //5. Create Reservation

        Reservation reservation1 = new Reservation(
                "Ali Ahmadi",
                "09121234567",
                1L,
                2,
                Status.ACTIVE
        );

        Reservation reservation2 = new Reservation(
                "Sara Mohammadi",
                "09123334455",
                2L,
                4,
                Status.ACTIVE
        );

        Reservation reservation3 = new Reservation(
                "Reza Karimi",
                "09125556677",
                3L,
                1,
                Status.ACTIVE
        );

        Reservation reservation4 = new Reservation(
                "Niloofar Hosseini",
                "09127778899",
                1L,
                3,
                Status.ACTIVE
        );

        Reservation reservation5 = new Reservation(
                "Mohammad Ebrahimi",
                "09129990011",
                2L,
                5,
                Status.ACTIVE
        );


        //==================================================================================================
        //6. Cancel Reservation

        reservationLogic.CancelReservation(2L);



        //==================================================================================================
        //7. Show All Reservations


        reservationLogic.ShowAllReservations();



        //==================================================================================================
        //8. Reports
        reportsData.TotalNumberOfActiveEvents();

        System.out.println();
        reportsData.AverageTicketPriceOfAllEvents();

        System.out.println();

        List<Reservation>reservations = reportsData.ListOfActiveReservations();
        System.out.println(reservations);

        System.out.println();

        List<Event>events = reportsData.ListOfFullyBookedEvents();
        System.out.println(events);

        System.out.println();

        reportsData.MostExpensiveEvent();



    }
}
