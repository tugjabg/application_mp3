package com.example.applicationmp3.Service;

public class APIService {
    private static String base_url = "https://projecti20201.000webhostapp.com/Server/";
    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
