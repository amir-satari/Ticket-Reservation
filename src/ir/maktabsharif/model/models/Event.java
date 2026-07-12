package ir.maktabsharif.model.models;

import ir.maktabsharif.model.basemodel.BaseModel;
import ir.maktabsharif.model.enums.Status;

public class Event extends BaseModel {
    private String title;
    private String location;
    private int capacity;
    private int reserved_count;
    private double ticket_price;
    private Status status;

    public Event(String title, String location, int capacity, int reserved_count, double ticket_price, Status status) {
        this.title = title;
        this.location = location;
        this.capacity = capacity;
        this.reserved_count = reserved_count;
        this.ticket_price = ticket_price;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getReserved_count() {
        return reserved_count;
    }

    public void setReserved_count(int reserved_count) {
        this.reserved_count = reserved_count;
    }

    public double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", reserved_count=" + reserved_count +
                ", ticket_price=" + ticket_price +
                ", status=" + status +
                '}';
    }
}
