package com.agh.lab2_pokemonrestapp.fightreferee;

import com.agh.lab2_pokemonrestapp.model.PokemonType;
import com.agh.lab2_pokemonrestapp.model.TypeData;

public class FightReferee {

    public String getResultMessage(String firstName, String secondName, PokemonType firstPokemonType, TypeData opponentTypeData) {
        FightResult fightResult = calculateFight(firstPokemonType, opponentTypeData);
        if (fightResult.equals(FightResult.FIRST_WON)) {
            return firstName + " wins!";
        } else if (fightResult.equals(FightResult.SECOND_WON)) {
            return secondName + " wins!";
        } else {
            return "It's a draw!";
        }
    }

    private FightResult calculateFight(PokemonType firstPokemonType, TypeData opponentTypeData) {
        String firstPokemonTypeName = firstPokemonType.getTypes().get(0);
        if (opponentTypeData.getTypesThatCountYou().contains(firstPokemonTypeName)) {
            return FightResult.FIRST_WON;
        } else if (opponentTypeData.getTypesThatYouCount().contains(firstPokemonTypeName))
            return FightResult.SECOND_WON;
        else return FightResult.DRAW;
    }
}
