package ir.maktabsharif.repository.Impl;

import ir.maktabsharif.exception.ReposioryLayerException;
import ir.maktabsharif.model.enums.Status;
import ir.maktabsharif.model.models.Event;
import ir.maktabsharif.repository.GenericRepository;
import ir.maktabsharif.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventRepositoryImpl implements GenericRepository<Event> {

    @Override
    public Boolean Save(Event event) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO Event(title, location, capacity, reserved_count, ticket_price, status) \n" +
                "VALUES (?,?,?,?,?,?) RETURNING id";

        try (PreparedStatement ps = connection.prepareStatement(query)){

            ps.setString(1,event.getTitle());
            ps.setString(2,event.getLocation());
            ps.setInt(3,event.getCapacity());
            ps.setInt(4,event.getReserved_count());
            ps.setDouble(5,event.getTicket_price());
            ps.setString(6,event.getStatus().toString());

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                event.setId(rs.getLong(1));
                return true;
            }
            return false;
        }catch (SQLException e){
            throw new ReposioryLayerException("The operation encountered a problem");
        }
    }

    @Override
    public Event Update(Event event) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE Event set title = ?,location = ?,capacity = ?,reserved_count = ?,ticket_price = ?,status = ? where id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1,event.getTitle());
            ps.setString(2,event.getLocation());
            ps.setInt(3,event.getCapacity());
            ps.setInt(4,event.getReserved_count());
            ps.setDouble(5,event.getTicket_price());
            ps.setString(6,event.getStatus().toString());

            ps.setLong(7,event.getId());

            int rowsAffects = ps.executeUpdate();

            if (rowsAffects > 0){
                return event;
            }
            else return null;

        }catch (SQLException e){
            throw new ReposioryLayerException("The operation encountered a problem");
        }
    }

    @Override
    public boolean Delete(Long id) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "DELETE FROM Event WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setLong(1,id);
            int rowsAffects = ps.executeUpdate();
            return rowsAffects > 0;
        }catch (SQLException e){
            throw new ReposioryLayerException("The operation encountered a problem");
        }
    }

    @Override
    public Event FindById(Long id) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM Event where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setLong(1,id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                Event event = new Event(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getDouble(6),
                        Status.valueOf(resultSet.getString(7))
                );
                event.setId(resultSet.getLong(1));
                return event;

            }
            return null;
        }catch (SQLException e ){
            throw new ReposioryLayerException("The operation encountered a problem");
        }
    }

    @Override
    public List<Event> FIndAll() {
        List<Event> events = new ArrayList<>();

        Connection connection = DatabaseConnection.getConnection();
        String query ="SELECT * FROM Event";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Event event = new Event(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getDouble(6),
                        Status.valueOf(resultSet.getString(7))
                );
                event.setId(resultSet.getLong(1));
                events.add(event);
            }
            return events;
        } catch (SQLException e) {
            throw new ReposioryLayerException("The operation encountered a problem");
        }

    }
}
