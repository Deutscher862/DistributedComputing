package com.agh.lab2_pokemonrestapp;

import com.agh.lab2_pokemonrestapp.datafinder.PokemonImageUrlFinder;
import com.agh.lab2_pokemonrestapp.datafinder.PokemonTypeFinder;
import com.agh.lab2_pokemonrestapp.datafinder.TypeDataFinder;
import com.agh.lab2_pokemonrestapp.fightreferee.FightReferee;
import com.agh.lab2_pokemonrestapp.fightreferee.FightResult;
import com.agh.lab2_pokemonrestapp.model.Pokemon;
import com.agh.lab2_pokemonrestapp.model.PokemonImageUrl;
import com.agh.lab2_pokemonrestapp.model.PokemonType;
import com.agh.lab2_pokemonrestapp.model.TypeData;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Service
class PokemonService {
    public void getParameters(Pokemon pokemon) {
        try {
            PokemonImageUrlFinder pokemonDataFinder = new PokemonImageUrlFinder();
            //TODO split this into seperated threads
            PokemonImageUrl firstPokemonImageUrl = pokemonDataFinder.findData(pokemon.getFirstName());
            PokemonImageUrl secondPokemonImageUrl = pokemonDataFinder.findData(pokemon.getSecondName());
            pokemon.setFirstPokemonImageUrl(firstPokemonImageUrl);
            pokemon.setSecondPokemonImageUrl(secondPokemonImageUrl);
            pokemon.setFirstName(StringUtils.capitalize(pokemon.getFirstName()));
            pokemon.setSecondName(StringUtils.capitalize(pokemon.getSecondName()));

            PokemonTypeFinder pokemonTypeFinder = new PokemonTypeFinder();
            PokemonType firstPokemonType = pokemonTypeFinder.findData(pokemon.getFirstName());
            PokemonType secondPokemonType = pokemonTypeFinder.findData(pokemon.getSecondName());
            pokemon.setFirstPokemonType(firstPokemonType);
            pokemon.setSecondPokemonType(secondPokemonType);

            //TODO what to do if request didnt succeed?

            TypeDataFinder typeDataFinder = new TypeDataFinder();
            //TODO this can possibly throw index out of range?
            TypeData typeData = typeDataFinder.findData(secondPokemonType.getTypes().get(0));

            FightReferee fightReferee = new FightReferee();
            FightResult fightResult = fightReferee.calculateFight(firstPokemonType, typeData);

            String resultMessage;
            if (fightResult.equals(FightResult.FIRST_WON)) {
                resultMessage = pokemon.getFirstName() + " wins!";
            } else if (fightResult.equals(FightResult.SECOND_WON)) {
                resultMessage = pokemon.getSecondName() + " wins!";
            } else {
                resultMessage = "It's a draw!";
            }
            pokemon.setResultMessage(resultMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
