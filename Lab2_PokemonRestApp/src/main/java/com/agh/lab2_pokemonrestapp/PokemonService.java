package com.agh.lab2_pokemonrestapp;

import com.agh.lab2_pokemonrestapp.datafinder.PokemonDataFinder;
import com.agh.lab2_pokemonrestapp.datafinder.TypeDataFinder;
import com.agh.lab2_pokemonrestapp.fightreferee.FightReferee;
import com.agh.lab2_pokemonrestapp.fightreferee.FightResult;
import com.agh.lab2_pokemonrestapp.model.Pokemon;
import com.agh.lab2_pokemonrestapp.model.PokemonData;
import com.agh.lab2_pokemonrestapp.model.TypeData;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Service
class PokemonService {
    public void getParameters(Pokemon pokemon) {
        try {
            PokemonDataFinder pokemonDataFinder = new PokemonDataFinder();
            //TODO split this into seperated threads
            PokemonData firstPokemonData = pokemonDataFinder.findData(pokemon.getFirstName());
            PokemonData secondPokemonData = pokemonDataFinder.findData(pokemon.getSecondName());
            pokemon.setFirstPokemonData(firstPokemonData);
            pokemon.setSecondPokemonData(secondPokemonData);
            pokemon.setFirstName(StringUtils.capitalize(pokemon.getFirstName()));
            pokemon.setSecondName(StringUtils.capitalize(pokemon.getSecondName()));

            //TODO what to do if request didnt succeed?

            TypeDataFinder typeDataFinder = new TypeDataFinder();
            //this can possibly throw index out of range?
            String firstPokemonType = pokemon.getFirstPokemonData().getTypes().get(0);
            String secondPokemonType = pokemon.getSecondPokemonData().getTypes().get(0);
            TypeData typeData = typeDataFinder.findData(secondPokemonType);

            FightReferee fightReferee = new FightReferee();
            FightResult fightResult = fightReferee.calculateFight(firstPokemonType, typeData);

            String resultMessage;
            if (fightResult.equals(FightResult.FIRST_WON)) {
                resultMessage = pokemon.getFirstName() + "wins!";
            } else if (fightResult.equals(FightResult.SECOND_WON)) {
                resultMessage = pokemon.getSecondName() + "wins!";
            } else {
                resultMessage = "It's a draw!";
            }
            pokemon.setResultMessage(resultMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
