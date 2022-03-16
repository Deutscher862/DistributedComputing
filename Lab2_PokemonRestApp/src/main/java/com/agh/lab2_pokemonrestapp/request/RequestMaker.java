package com.agh.lab2_pokemonrestapp.request;

import com.agh.lab2_pokemonrestapp.Commons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestMaker {

    public String findPokemon(String pokemonName) throws IOException {
        URL url = new URL(Commons.POKEAPI_URL + pokemonName);
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
