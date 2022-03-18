package com.agh.lab2_pokemonrestapp.fightreferee;

import com.agh.lab2_pokemonrestapp.model.TypeData;

public class FightReferee {

    public FightResult calculateFight(String firstPokemonType, TypeData opponentTypeData) {
        for (String typeName : opponentTypeData.getTypesThatCountYou()){
            if(firstPokemonType.equals(typeName))
                return FightResult.FIRST_WON;
        }
        for (String typeName : opponentTypeData.getTypesThatYouCount()){
            if(firstPokemonType.equals(typeName))
                return FightResult.SECOND_WON;
        }
        return FightResult.DRAW;
    }
}
