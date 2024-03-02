package ru.batov.DAO;

import ru.batov.config.JdbsConnections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDaoJdbs {

    public List<String> getListTrack(String status){
        List<String> listTrack = new ArrayList<>();
        Connection connection = JdbsConnections.PochtaBase();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT Track FROM Track WHERE Status = ?");
            preparedStatement.setString(1, status);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listTrack.add(resultSet.getString("Track"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTrack;
    }

    public void updateCreateDateTrack(String track, String date) {
        Connection connection = JdbsConnections.PochtaBase();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Track SET CreateTrack = ? WHERE Track = ?");
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, track);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void uodateStatusTrack(String track, String status){
        Connection connection = JdbsConnections.PochtaBase();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Track SET Status = ? WHERE Track = ?");
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, track);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTrackHistoryToDatabase(String track, String json, String name, String lastHistory) {
        Connection connection = JdbsConnections.PochtaBase();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO TrackHistory (Track, Json, Name, LastHistory) " +
                            "VALUES (?, ?, ?, ?)");

            preparedStatement.setString(1, track);
            preparedStatement.setString(2, json);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, lastHistory);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE constraint failed")) {
            } else {
                e.printStackTrace();
            }
        }
    }

    public void updateTrackHistoryInDatabase(String track, String json, String name, String lastHistory) {
        Connection connection = JdbsConnections.PochtaBase();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE TrackHistory SET Json = ?, Name = ?, LastHistory = ? WHERE Track = ?");

            preparedStatement.setString(1, json);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastHistory);
            preparedStatement.setString(4, track);

            int rowsUpdated = preparedStatement.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
