CREATE DATABASE Ticket_Reservation;

CREATE TABLE Event(
                      id BIGSERIAL PRIMARY KEY ,
                      title VARCHAR(50),
                      location VARCHAR(50),
                      capacity INTEGER,
                      reserved_count INTEGER,
                      ticket_price DECIMAL,
                      status VARCHAR(50)
);



CREATE TABLE Reservation(
                            id BIGSERIAL PRIMARY KEY ,
                            customer_name VARCHAR(50),
                            customer_phone VARCHAR(50),
                            event_id BIGINT,
                            ticket_count INTEGER,
                            reservation_date DATE,
                            status VARCHAR(50)
);


--======================================================================

INSERT INTO Event(title, location, capacity, reserved_count, ticket_price, status)
VALUES (?,?,?,?,?,?);



--======================================================================

