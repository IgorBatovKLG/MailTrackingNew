package ru.batov;

import ru.batov.DAO.PatientDaoJdbs;
import ru.batov.models.PatientModel;
import ru.batov.services.PatientService;

public class Main {
    public static void main(String[] args) {
        PatientService service = new PatientService();
        service.addDbPatientDay("2024-02-27");

    }
}