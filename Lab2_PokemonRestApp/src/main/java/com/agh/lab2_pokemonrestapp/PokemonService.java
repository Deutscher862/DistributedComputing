package com.agh.lab2_pokemonrestapp;

import com.agh.lab2_pokemonrestapp.datafinder.PokemonDataFinder;
import com.agh.lab2_pokemonrestapp.model.Pokemon;
import com.agh.lab2_pokemonrestapp.model.PokemonData;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Service
class PokemonService {
    public void getParameters(Pokemon pokemon) {
        try {
            PokemonDataFinder dataFinder = new PokemonDataFinder();
            PokemonData pokemonData = dataFinder.findData(pokemon.getName());
            pokemon.setImageUrl(pokemonData.getImageUrl());
            pokemon.setTypes(pokemonData.getTypes());
            pokemon.setName(StringUtils.capitalize(pokemon.getName()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
