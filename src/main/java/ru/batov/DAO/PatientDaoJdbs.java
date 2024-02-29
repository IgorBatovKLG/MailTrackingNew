package ru.batov.DAO;

import ru.batov.config.JdbsConnections;
import ru.batov.models.PatientModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientDaoJdbs {

    public void addPatientToDatabase(PatientModel patient) {
        Connection connection = JdbsConnections.PochtaBase();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Patients (Hash, ProtocolNumber, ExamBuroName, DecisionDate, LastName, FirstName, SecondName, PurposesXml, Days) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, patient.getId());
            preparedStatement.setString(2, patient.getProtocolNumber());
            preparedStatement.setString(3, patient.getExamBuroName());
            preparedStatement.setString(4, patient.getDecisionDate());
            preparedStatement.setString(5, patient.getLastName());
            preparedStatement.setString(6, patient.getFirstName());
            preparedStatement.setString(7, patient.getSecondName());
            preparedStatement.setString(8, patient.getPurposesXml());
            preparedStatement.setString(9, "null");

            preparedStatement.executeUpdate();
            System.out.println("Patient added to database successfully!");

        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE constraint failed")){

            }else {
                e.printStackTrace();
            }
        }
    }


}
