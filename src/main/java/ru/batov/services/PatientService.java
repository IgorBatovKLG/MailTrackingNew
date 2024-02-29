package ru.batov.services;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import ru.batov.DAO.PatientDaoJdbs;
import ru.batov.models.PatientModel;

import java.util.ArrayList;
import java.util.List;

public class PatientService {

    private List<PatientModel> patientModelList(String date){
        //2024-02-28
        Gson gson = new Gson();
        EvaGetDataService evaGetDataService = new EvaGetDataService();

        String json = "{\"Columns\":[\"ProtocolNumber\",\"ExamBuroName\",\"DecisionDate\",\"LastName\",\"FirstName\",\"SecondName\",\"PurposesXml\"],\"Conditions\":[{\"FieldName\":\"DecisionDate\",\"Type\":9,\"IsNegative\":false,\"Disabled\":false,\"Value\":null,\"Values\":[\""+date+"T00:00:00\",\""+date+"T00:00:00\"]}],\"HidePeopleDoubles\":false,\"Page\":1,\"PageSize\":250,\"SortField\":null,\"IsSortDesc\":false,\"InvalidateCache\":true}";

        String stringJson = evaGetDataService.getStringJson(json);
        String list = JsonPath.parse(stringJson).read("List").toString();
        PatientModel[] patientModels = gson.fromJson(list, PatientModel[].class);
        List<PatientModel> models = List.of(patientModels);
        return models;
    }

    private void addDbPatient(PatientModel model){
        PatientDaoJdbs patientDaoJdbs = new PatientDaoJdbs();
        switch (model.getExamBuroName().replace(" ", "")){
            case("Бюромедико-социальнойэкспертизы№1"):model.setExamBuroName("201");break;
            case("Бюромедико-социальнойэкспертизы№2"):model.setExamBuroName("202");break;
            case("Бюромедико-социальнойэкспертизы№3"):model.setExamBuroName("203");break;
            case("Бюромедико-социальнойэкспертизы№4смешанногопрофиля"):model.setExamBuroName("204");break;
            case("Бюромедико-социальнойэкспертизы№5дляосвидетельствованиялицввозрастедо18лет"):model.setExamBuroName("205");break;
            case("Бюромедико-социальнойэкспертизы№6дляосвидетельствованиялицспсихическимирасстройствами"):model.setExamBuroName("206");break;
            case("Бюромедико-социальнойэкспертизы№7смешанногопрофиля"):model.setExamBuroName("207");break;
            case("Бюромедико-социальнойэкспертизы№8дляосвидетельствованиялицсзаболеваниямиидефектамиорганазрения"):model.setExamBuroName("208");break;
            case("Бюромедико-социальнойэкспертизы№9смешанногопрофиля"):model.setExamBuroName("209");break;
            case("Бюромедико-социальнойэкспертизы№10"):model.setExamBuroName("210");break;
            case("Бюромедико-социальнойэкспертизы№11смешанногопрофиля"):model.setExamBuroName("211");break;
            case("Бюромедико-социальнойэкспертизы№12смешанногопрофиля"):model.setExamBuroName("212");break;
            case("Бюромедико-социальнойэкспертизы№13"):model.setExamBuroName("213");break;
            case("Бюромедико-социальнойэкспертизы№15смешанногопрофиля"):model.setExamBuroName("215");break;
            case("Бюромедико-социальнойэкспертизы№17"):model.setExamBuroName("217");break;
            case("Бюромедико-социальнойэкспертизы№18"):model.setExamBuroName("218");break;
            case("Бюромедико-социальнойэкспертизы№19"):model.setExamBuroName("219");break;
            case("Экспертныйсостав№1смешанногопрофиля"):model.setExamBuroName("301");break;
            case("Экспертныйсостав№2смешанногопрофиля"):model.setExamBuroName("302");break;
            case("Экспертныйсостав№3смешанногопрофиля"):model.setExamBuroName("303");break;
        }
        patientDaoJdbs.addPatientToDatabase(model);
    }

    public void addDbPatientDay(String date){
        List<PatientModel> models = patientModelList(date);
        for (PatientModel model : models) {
            addDbPatient(model);
        }
    }
}
