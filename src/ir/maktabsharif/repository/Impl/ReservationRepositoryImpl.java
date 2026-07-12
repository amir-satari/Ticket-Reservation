package ir.maktabsharif.repository.Impl;

import ir.maktabsharif.exception.ReposioryLayerException;
import ir.maktabsharif.model.enums.Status;
import ir.maktabsharif.model.models.Reservation;
import ir.maktabsharif.repository.GenericRepository;
import ir.maktabsharif.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepositoryImpl implements GenericRepository<Reservation> {
    @Override
    public Boolean Save(Reservation reservation) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO Reservation(customer_name, customer_phone, event_id, ticket_count, reservation_date, status) \n" +
                "VALUES (?,?,?,?,?,?) RETURNING id";

        try (PreparedStatement ps = connection.prepareStatement(query)){

            ps.setString(1,reservation.getCustomer_name());
            ps.setString(2,reservation.getCustomer_phone());
            ps.setLong(3,reservation.getEvent_id());
            ps.setInt(4,reservation.getTicket_count());
            ps.setDate(5,reservation.getReservation_date());
            ps.setString(6,reservation.getStatus().toString());

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()){
                reservation.setId(resultSet.getLong(1));
                return true;
            }
            return false;
        }catch (SQLException e){
            throw new ReposioryLayerException("The operation encountered a problem");
        }
    }

    @Override
    public Reservation Update(Reservation reservation) {
        Connection connection = DatabaseConnection.getConnection();
        String query ="UPDATE Reservation set customer_name =?,customer_phone =?,event_id =?,ticket_count =?, reservation_date=?,status =? where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1,reservation.getCustomer_name());
            ps.setString(2,reservation.getCustomer_phone());
            ps.setLong(3,reservation.getEvent_id());
            ps.setInt(4,reservation.getTicket_count());
            ps.setString(5,reservation.getStatus().toString());

            ps.setLong(6,reservation.getId());

            int rowsAffects = ps.executeUpdate();

            if (rowsAffects> 0){
                return reservation;
            }
            return null;

        }catch (SQLException e){
            throw new ReposioryLayerException("The operation encountered a problem");
        }
    }

    @Override
    public boolean Delete(Long id) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "DELETE FROM Reservation WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setLong(1,id);
            int rowsAffects = ps.executeUpdate();
            return rowsAffects > 0;
        }catch (SQLException e){
            throw new ReposioryLayerException("The operation encountered a problem");
        }
    }

    @Override
    public Reservation FindById(Long id) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM Reservation WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                Reservation reservation = new Reservation(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getLong(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        Status.valueOf(rs.getString(7))
                );
                reservation.setId(id);

                return reservation;
            }
            return null;
        }catch (SQLException e){
            throw new ReposioryLayerException("The operation encountered a problem");
        }
    }

    @Override
    public List<Reservation> FIndAll() {
        List<Reservation> reservations = new ArrayList<>();
        Connection connection =DatabaseConnection.getConnection();
        String query = "SELECT * FROM Reservation";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Reservation reservation = new Reservation(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getLong(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        Status.valueOf(rs.getString(7))
                );
                reservation.setId(rs.getLong(1));
                reservations.add(reservation);
            }
            return reservations;
        }catch (SQLException e){
            throw new ReposioryLayerException("The operation encountered a problem");
        }
    }
}
