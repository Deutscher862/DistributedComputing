package com.agh.lab2_pokemonrestapp.datafinder;

import com.agh.lab2_pokemonrestapp.Commons;
import com.agh.lab2_pokemonrestapp.datafinder.request.RequestMaker;
import com.agh.lab2_pokemonrestapp.model.PokemonType;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class PokemonTypeFinder implements DataFinder {
    @Override
    public PokemonType findData(String pokemonName) throws IOException {
        try {
            URL url = new URL(Commons.POKEMONTCG_POKEMON_TYPE + pokemonName.toLowerCase());
            RequestMaker requestMaker = new RequestMaker();
            String json = requestMaker.sendGetRequest(url);
            PokemonType pokemonType = new Gson().fromJson(json, PokemonType.class);
            pokemonType.decapitalizeNames();
            return pokemonType;
        } catch (FileNotFoundException e) {
            Commons.ERROR_MESSAGE = pokemonName + " is not a Pokemon name!";
            return null;
        }
    }
}

