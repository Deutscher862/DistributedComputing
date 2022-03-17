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
            PokemonData firstPokemonData = dataFinder.findData(pokemon.getFirstName());
            PokemonData secondPokemonData = dataFinder.findData(pokemon.getSecondName());
            pokemon.setFirstPokemonData(firstPokemonData);
            pokemon.setSecondPokemonData(secondPokemonData);
            pokemon.setFirstName(StringUtils.capitalize(pokemon.getFirstName()));
            pokemon.setSecondName(StringUtils.capitalize(pokemon.getSecondName()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
