package ir.maktabsharif;

import ir.maktabsharif.model.enums.Status;
import ir.maktabsharif.model.models.Event;
import ir.maktabsharif.model.models.Reservation;
import ir.maktabsharif.service.EventLogic;
import ir.maktabsharif.service.ReportsData;
import ir.maktabsharif.service.ReservationLogic;

import java.sql.Date;
import java.util.List;

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
        System.out.println("1. Create Event");

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

        System.out.println();
        //======================================================

        //2. Show All Events

        System.out.println("Show All Events");
        eventLogic.ShowAllEvents();


        System.out.println();
        //======================================================


        //3. Update Event
        //4. Cancel Event

        System.out.println("Update Event    Cancel Event");

        event2.setStatus(Status.CANCELLED);
        event2.setLocation("new york");
        eventLogic.UpdateEvent(event2);


        System.out.println();
        //======================================================

        //5. Create Reservation

        System.out.println("Create Reservation");
        Reservation reservation1 = new Reservation(
                "Ali Ahmadi",
                "09121234567",
                event1.getId(),
                2,
                Date.valueOf("2026-01-12"),
                 Status.ACTIVE
        );

        Reservation reservation2 = new Reservation(
                "Sara Mohammadi",
                "09123334455",
                event2.getId(),
                4,
                Date.valueOf("2026-05-25"),
                 Status.ACTIVE
        );

        Reservation reservation3 = new Reservation(
                "Reza Karimi",
                "09125556677",
                event5.getId(),
                10,
                Date.valueOf("2027-11-12"),
                 Status.ACTIVE
        );

        Reservation reservation4 = new Reservation(
                "Niloofar Hosseini",
                "09127778899",
                event2.getId(),
                13,
                Date.valueOf("2026-10-10"),
                 Status.ACTIVE
        );

        Reservation reservation5 = new Reservation(
                "Mohammad Ebrahimi",
                "09129990011",
                event3.getId(),
                15,
                Date.valueOf("2026-12-12"),
                 Status.ACTIVE
        );


        reservationLogic.CreateReservation(reservation1);
        reservationLogic.CreateReservation(reservation2);
        reservationLogic.CreateReservation(reservation3);
        reservationLogic.CreateReservation(reservation4);
        reservationLogic.CreateReservation(reservation5);


        System.out.println();
        //==================================================================================================
        //6. Cancel Reservation

        System.out.println("Cancel Reservation");
        reservationLogic.CancelReservation(reservation3.getId());


        System.out.println();
        //==================================================================================================
        //7. Show All Reservations


        System.out.println("Show All Reservations");
        reservationLogic.ShowAllReservations();


        System.out.println();
        //==================================================================================================
        //8. Reports

        System.out.println("Reports");

        System.out.println("TotalNumberOfActiveEvents");
        reportsData.TotalNumberOfActiveEvents();

        System.out.println();
        System.out.println("AverageTicketPriceOfAllEvents");
        reportsData.AverageTicketPriceOfAllEvents();

        System.out.println();

        System.out.println("ListOfActiveReservations");
        List<Reservation>reservations = reportsData.ListOfActiveReservations();
        reservations.stream().forEach(System.out::println);

        System.out.println();

        System.out.println("ListOfFullyBookedEvents");
        List<Event>events = reportsData.ListOfFullyBookedEvents();
        events.stream().forEach(System.out::println);

        System.out.println();

        System.out.println("MostExpensiveEvent");
        reportsData.MostExpensiveEvent();


        System.out.println();
        System.out.println("CancelEvent");
        eventLogic.CancelEvent(event2.getId());

        System.out.println();
        System.out.println("isActive");
        System.out.println(eventLogic.isActive(event4)?"yes is active":"no is notactive");



    }
}
