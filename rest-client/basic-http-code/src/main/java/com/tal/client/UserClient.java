package com.tal.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserClient {
    private String baseUrl = "http://localhost:8080/users";
    public String getUsers() {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(baseUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", "Bearer Test");
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }

                reader.close();
            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
                return "GET request failed. Response Code: " + responseCode;
            }
        } catch (IOException exception) {
            exception.getLocalizedMessage();
            return exception.getLocalizedMessage();
        }
        return response.toString();
    }
}
