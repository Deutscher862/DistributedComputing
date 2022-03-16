package com.agh.lab2_pokemonrestapp;

import com.agh.lab2_pokemonrestapp.model.Pokemon;
import com.agh.lab2_pokemonrestapp.model.PokemonImageUrl;
import com.agh.lab2_pokemonrestapp.request.RequestMaker;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
class PokemonService {

    public void getParameters(Pokemon pokemon) {
        pokemon.setHealthPoints(10);

        RequestMaker requestMaker = new RequestMaker();
        try {
            String json = requestMaker.findPokemon(pokemon.getName());

            PokemonImageUrl pokemonImageUrl = new Gson().fromJson(json, PokemonImageUrl.class);
            pokemon.setImageUrl(pokemonImageUrl.getImageUrl());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
