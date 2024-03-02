package ru.batov.services;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import ru.batov.DAO.TrackDaoJdbs;
import ru.batov.models.TrackHistoryModel;

import java.lang.reflect.Array;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class TrackService {

    private String getJsonPochta(String track){
        System.out.println("Started checking " + track);
        String jsonPochta = "";
        try {
            String json = "[\"" + track + "\"]";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://www.pochta.ru/api/nano-apps/api/v1/tracking.get-by-barcodes?language=ru"))
                    .setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:99.0) Gecko/20100101 Firefox/99.0")
                    .header("Content-Type", "application/json")
                    .timeout(java.time.Duration.ofSeconds(10))
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            jsonPochta = response.body().toString();
        }catch (Exception e){
            System.out.println("Error Pochta.ru " + track + " | " + e.getMessage());
        }
        return jsonPochta;
    }

    public void processingListTrack(){
        TrackDaoJdbs trackDaoJdbs = new TrackDaoJdbs();
        List<String> listTrack = trackDaoJdbs.getListTrack("In-transit");
        for (String track : listTrack) {
            String json = getJsonPochta(track);
            processingTrack(json);
        }
    }

    private void processingTrack(String json){
        System.out.println("Json processing");
        Gson gson = new Gson();
        try {
            String name = JsonPath.parse(json).read("$..recipient").toString();
            String track = JsonPath.parse(json).read("$..barcode").toString();
            track = track.substring(2, track.length() - 2);
            String jsonHistory = JsonPath.parse(json).read("$..trackingHistoryItemList").toString();
            jsonHistory = jsonHistory.substring(1, jsonHistory.length() - 1);
            TrackHistoryModel[] trackModels = gson.fromJson(jsonHistory, TrackHistoryModel[].class);

            for (TrackHistoryModel trackModel : trackModels) {
                if (trackModel.getHumanStatus().equals("Присвоен трек-номер")) {//todo точное название статуса
                    updateCreateDate(track, trackModel.getDate().split("T")[0]);
                }
                if (trackModel.getHumanStatus().equals("Вручение адресату")
                        || trackModel.getHumanStatus().equals("Вручение адресату почтальоном")) {
                    updateStatusTrack(track, "Delivered");
                    System.out.println("Track status Delivered " + track);
                }
                if (trackModel.getHumanStatus().equals("Возврат отправителю по иным обстоятельствам")
                        || trackModel.getHumanStatus().equals("Возврат отправителю из-за истечения срока хранения")) {
                    updateStatusTrack(track, "Returned");
                    System.out.println("Track status Returned " + track);
                }
            }
            TrackHistoryModel lastTrackModel = trackModels[0];
            createHistoryTrack(track, json, name, lastTrackModel.getHumanStatus() + "|" + lastTrackModel.getDate().split("T")[0]);
            updateHistoryTrack(track, json, name, lastTrackModel.getHumanStatus() + "|" + lastTrackModel.getDate().split("T")[0]);


        } catch (Exception e){
            System.out.println("Error json "  + e.getMessage());
        }

    }

    private void updateCreateDate(String track, String date){
        TrackDaoJdbs trackDaoJdbs = new TrackDaoJdbs();
        trackDaoJdbs.updateCreateDateTrack(track, date);
    }

    private void updateHistoryTrack(String track, String json, String name, String lastHistory){
        TrackDaoJdbs trackDaoJdbs = new TrackDaoJdbs();
        trackDaoJdbs.updateTrackHistoryInDatabase(track, json, name, lastHistory);
    }

    private void createHistoryTrack(String track, String json, String name, String lastHistory){
        TrackDaoJdbs trackDaoJdbs = new TrackDaoJdbs();
        trackDaoJdbs.addTrackHistoryToDatabase(track, json, name, lastHistory);
    }

    private void updateStatusTrack(String track, String status){
        TrackDaoJdbs trackDaoJdbs = new TrackDaoJdbs();
        trackDaoJdbs.uodateStatusTrack(track,status);
    }






}
