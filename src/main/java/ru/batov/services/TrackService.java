package ru.batov.services;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import ru.batov.models.TrackModel;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class TrackService {

    public String getJsonPochta(String track){
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

        }
        return jsonPochta;
    }

    public List<String> getListTreck(){
        return null; //todo
    }

    public void processingTrack(String json){

        Gson gson = new Gson();
        String name = JsonPath.parse(json).read("$..recipient").toString();
        String jsonHistory = JsonPath.parse(json).read("$..trackingHistoryItemList").toString();
        jsonHistory = jsonHistory.substring(1, jsonHistory.length()-1);
        TrackModel[] trackModels = gson.fromJson(json, TrackModel[].class);
    }

    public void createLineInDb(String json){

        //todo загрузка всего этого чуда в бд
    }

    public void updateHistoryTrack(String json){
        //todo загрузка всего этого чуда в бд
    }






}