package ir.maktabsharif.model.models;

import ir.maktabsharif.model.basemodel.BaseModel;
import ir.maktabsharif.model.enums.Status;

import java.sql.Date;
import java.time.LocalDate;

public class Reservation extends BaseModel {
    private String customer_name;
    private String customer_phone;
    private Long event_id;
    private int ticket_count;
    private Date reservation_date;
    private Status status;


    public Reservation(String customer_name, String customer_phone, Long event_id, int ticket_count,  Status status) {
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
        this.event_id = event_id;
        this.ticket_count = ticket_count;
        this.reservation_date = Date.valueOf(LocalDate.now());
        this.status = status;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public Long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Long event_id) {
        this.event_id = event_id;
    }

    public int getTicket_count() {
        return ticket_count;
    }

    public void setTicket_count(int ticket_count) {
        this.ticket_count = ticket_count;
    }

    public Date getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(Date reservation_date) {
        this.reservation_date = reservation_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer_name='" + customer_name + '\'' +
                ", customer_phone='" + customer_phone + '\'' +
                ", event_id=" + event_id +
                ", ticket_count=" + ticket_count +
                ", reservation_date=" + reservation_date +
                ", status=" + status +
                '}';
    }
}
