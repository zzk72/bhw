package com.example.bhw.Bean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class HandleWebServiceBean {
    private String port;
    private String prefix;

    public HandleWebServiceBean() throws IOException {
        loadProperties();
    }

    private void loadProperties() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("RESTful.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find RESTful.properties");
                return;
            }
            properties.load(input);
            port = properties.getProperty("server.port");
            prefix = properties.getProperty("service.prefix").replace("${server.port}", port);
        }
    }

    public String handleViewWeather(String date, int viewpointId) throws IOException {
        String weatherInfo = getWebServiceData(prefix + "weather/forecast?date=" + date + "&viewpointId=" + viewpointId);
        if (weatherInfo == null || weatherInfo.isEmpty()) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(weatherInfo);
        return jsonObject.getString("weather");
    }

    public String handleViewAir(String date, int viewpointId) throws IOException {
        String airInfo = getWebServiceData(prefix + "weather/air?date=" + date + "&viewpointId=" + viewpointId);
        if (airInfo == null || airInfo.isEmpty()) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(airInfo);
        return jsonObject.getString("airQuality");
    }

    public String handleViewTemperature(String date, int viewpointId) throws IOException {
        String weatherInfo = getWebServiceData(prefix + "weather/temperature?date=" + date + "&viewpointId=" + viewpointId);
        if (weatherInfo == null || weatherInfo.isEmpty()) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(weatherInfo);
        return jsonObject.getString("temperature");
    }

    public List<String> handleViewReviews(int viewpointId) throws IOException {
        List<String> commentList = new ArrayList<>();
        String reviews = getWebServiceData(prefix + "reviews/viewpoint?viewpointId=" + viewpointId);
        if (reviews == null || reviews.isEmpty()) {
            return commentList;
        }
        JSONObject jsonObject = new JSONObject(reviews);
        JSONArray reviewsArray = jsonObject.getJSONArray("reviews");

        for (int i = 0; i < reviewsArray.length(); i++) {
            JSONObject reviewObject = reviewsArray.getJSONObject(i);
            commentList.add(reviewObject.getString("comment"));
        }

        return commentList;
    }

    private String getWebServiceData(String urlString) throws IOException {
        HttpURLConnection conn = null;
        Scanner scanner = null;
        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() != 200) {
                return "";
            }

            scanner = new Scanner(url.openStream());
            StringBuilder inline = new StringBuilder();
            while (scanner.hasNext()) {
                inline.append(scanner.nextLine());
            }
            return inline.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
