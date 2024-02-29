package ru.batov;


import ru.batov.services.PatientService;
import ru.batov.services.TrackService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        System.out.println(LocalDate.now() + " Start");
        Runnable patient = () -> {
            while (true) {
                PatientService patientService = new PatientService();
                patientService.addDbPatientDay(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                patientService.addDbPatientDay(LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                try {
                    Thread.sleep(1000 * 60 * 60 * 6);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable track = () -> {
            while (true) {
                TrackService trackService = new TrackService();
                trackService.processingListTrack();
                try {
                    Thread.sleep(1000 * 60 * 60 * 5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread threadPatient = new Thread(patient);
        Thread threadTrack = new Thread(track);
        threadPatient.start();
        threadTrack.start();
    }
}