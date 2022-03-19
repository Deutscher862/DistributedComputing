package com.agh.lab2_pokemonrestapp.datafinder;

import com.agh.lab2_pokemonrestapp.Commons;
import com.agh.lab2_pokemonrestapp.model.PokemonImageUrl;
import com.agh.lab2_pokemonrestapp.datafinder.request.RequestMaker;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class PokemonImageUrlFinder implements DataFinder{
    @Override
    public PokemonImageUrl findData(String pokemonName) throws IOException {
        try {
            URL url = new URL(Commons.POKEAPI_POKEMON_DATA + pokemonName.toLowerCase());
            RequestMaker requestMaker = new RequestMaker();
            String json = requestMaker.sendGetRequest(url);
            return new Gson().fromJson(json, PokemonImageUrl.class);
        } catch (FileNotFoundException e) {
            Commons.ERROR_MESSAGE = pokemonName + " is not a Pokemon name!";
            return null;
        }
    }
}
