package ru.batov.DAO;

import ru.batov.config.JdbsConnections;
import ru.batov.models.TrackModel;

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


}
