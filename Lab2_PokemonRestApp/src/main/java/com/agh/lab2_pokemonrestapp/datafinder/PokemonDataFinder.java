package com.agh.lab2_pokemonrestapp.datafinder;

import com.agh.lab2_pokemonrestapp.Commons;
import com.agh.lab2_pokemonrestapp.model.PokemonData;
import com.agh.lab2_pokemonrestapp.datafinder.request.RequestMaker;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;

public class PokemonDataFinder {
    public PokemonData findData(String name) throws IOException {
        URL url = new URL(Commons.POKEAPI_URL + name.toLowerCase());
        RequestMaker requestMaker = new RequestMaker();
        String json = requestMaker.sendGetRequest(url);
        PokemonData pokemonData = new Gson().fromJson(json, PokemonData.class);
        return pokemonData;
    }
}
