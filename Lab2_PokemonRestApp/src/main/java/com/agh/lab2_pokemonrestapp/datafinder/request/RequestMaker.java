package com.agh.lab2_pokemonrestapp.datafinder.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestMaker {

    public String sendGetRequest(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        StringBuilder content = new StringBuilder();
        while ((line = input.readLine()) != null) {
            content.append(line);
        }
        input.close();
        connection.disconnect();

        return content.toString();
    }
}
