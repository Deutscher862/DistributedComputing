package com.agh.lab2_pokemonrestapp.fightreferee;

import com.agh.lab2_pokemonrestapp.model.PokemonType;
import com.agh.lab2_pokemonrestapp.model.TypeData;

public class FightReferee {

    public FightResult calculateFight(PokemonType firstPokemonType, TypeData opponentTypeData) {
        String firstPokemonTypeName = firstPokemonType.getTypes().get(0);
        for (String typeName : opponentTypeData.getTypesThatCountYou()) {
            if (firstPokemonTypeName.equalsIgnoreCase(typeName))
                return FightResult.FIRST_WON;
        }
        for (String typeName : opponentTypeData.getTypesThatYouCount()) {
            if (firstPokemonTypeName.equalsIgnoreCase(typeName))
                return FightResult.SECOND_WON;
        }
        return FightResult.DRAW;
    }
}
