package com.agh.lab2_pokemonrestapp.fightreferee;

import com.agh.lab2_pokemonrestapp.model.PokemonType;
import com.agh.lab2_pokemonrestapp.model.TypeData;

public class FightReferee {

    public FightResult calculateFight(PokemonType firstPokemonType, TypeData opponentTypeData) {
        String firstPokemonTypeName = firstPokemonType.getTypes().get(0);
        if (opponentTypeData.getTypesThatCountYou().contains(firstPokemonTypeName)) {
            return FightResult.FIRST_WON;
        } else if (opponentTypeData.getTypesThatYouCount().contains(firstPokemonTypeName))
            return FightResult.SECOND_WON;
        else return FightResult.DRAW;
    }
}
