package com.example.bhw.Bean;





import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HandleWebServiceBean {
    String port = "8081";
    String prefix = "http://localhost:" + port + "/wild_restful-1.0-SNAPSHOT/api/";
    public String handleViewWeather(String date,int viewpointId) throws IOException {
        String weatherInfo = getWebServiceData(prefix + "weather/forecast?date=" + date + "&viewpointId=" + viewpointId);
        JSONObject jsonObject = new JSONObject(weatherInfo);
        String weather = (String) jsonObject.get("weather");
        return weather;
    }
    public String handleViewAir(String date,int viewpointId) throws IOException {
        String airInfo = getWebServiceData(prefix+"weather/air?date=" + date + "&viewpointId=" + viewpointId);
        JSONObject jsonObject = new JSONObject(airInfo);
        String airQuality = (String) jsonObject.get("airQuality");
        return airQuality;
    }


    public String handleViewTemperature(String date,int viewpointId) throws IOException {
        String weatherInfo = getWebServiceData(prefix+"weather/temperature?date=" + date + "&viewpointId=" + viewpointId);
        JSONObject jsonObject = new JSONObject(weatherInfo);
        String temperature = (String) jsonObject.get("temperature");
        return temperature;
    }

//    public List<String> handleViewReviews(int viewpointId) throws IOException {
//        List<String> commentList = new ArrayList<>();
//        String reviews = getWebServiceData("http://localhost:8080/bhw-1.0-SNAPSHOT/api/reviews/viewpoint?viewpointId=" + viewpointId);
//        JSONObject jsonObject = new JSONObject(reviews);
//        List<Object> reviewsList = jsonObject.getJSONArray("reviews").toList();
//        for (Object review : reviewsList) {
//            Map<String, String> reviewMap = (Map<String, String>) review;
//            commentList.add(reviewMap.get("comment"));
//        }
//        return commentList;
//    }
    public List<String> handleViewReviews(int viewpointId) throws IOException {
        List<String> commentList = new ArrayList<>();
        String reviews = getWebServiceData(prefix+"reviews/viewpoint?viewpointId=" + viewpointId);
        JSONObject jsonObject = new JSONObject(reviews);
        JSONArray reviewsArray = jsonObject.getJSONArray("reviews");

        for (int i = 0; i < reviewsArray.length(); i++) {
            JSONObject reviewObject = reviewsArray.getJSONObject(i);
            String comment = reviewObject.getString("comment");
            commentList.add(comment);
        }

        return commentList;
    }
    private String getWebServiceData(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        Scanner scanner = new Scanner(url.openStream());
        StringBuilder inline = new StringBuilder();
        while (scanner.hasNext()) {
            inline.append(scanner.nextLine());
        }
        scanner.close();
        return inline.toString();
    }
}
