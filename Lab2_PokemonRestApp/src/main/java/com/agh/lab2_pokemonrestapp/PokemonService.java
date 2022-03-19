package com.agh.lab2_pokemonrestapp;

import com.agh.lab2_pokemonrestapp.datafinder.PokemonImageUrlFinder;
import com.agh.lab2_pokemonrestapp.datafinder.PokemonTypeFinder;
import com.agh.lab2_pokemonrestapp.datafinder.TypeDataFinder;
import com.agh.lab2_pokemonrestapp.fightreferee.FightReferee;
import com.agh.lab2_pokemonrestapp.model.Pokemon;
import com.agh.lab2_pokemonrestapp.model.PokemonImageUrl;
import com.agh.lab2_pokemonrestapp.model.PokemonType;
import com.agh.lab2_pokemonrestapp.model.TypeData;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
class PokemonService {
    private final ExecutorService executor = Executors.newCachedThreadPool();

    private Future<PokemonImageUrl> getImageUrl(String pokemonName) {
        return executor.submit(() -> {
            PokemonImageUrlFinder pokemonImageUrlFinder = new PokemonImageUrlFinder();
            return pokemonImageUrlFinder.findData(pokemonName);
        });
    }

    private Future<PokemonType> getPokemonType(String pokemonName) {
        return executor.submit(() -> {
            PokemonTypeFinder typeFinder = new PokemonTypeFinder();
            return typeFinder.findData(pokemonName);
        });
    }

    private Future<TypeData> getTypeData(String typeName) {
        return executor.submit(() -> {
            TypeDataFinder typeDataFinder = new TypeDataFinder();
            return typeDataFinder.findData(typeName);
        });
    }

    public String makeRequestsAndGetResult(Pokemon pokemon) {
        String firstName = pokemon.getFirstName();
        String secondName = pokemon.getSecondName();
        Future<PokemonImageUrl> response1 = getImageUrl(firstName);
        Future<PokemonImageUrl> response2 = getImageUrl(secondName);
        Future<PokemonType> response3 = getPokemonType(firstName);
        Future<PokemonType> response4 = getPokemonType(secondName);

        while (!response1.isDone() && !response2.isDone() && !response3.isDone() && !response4.isDone()) {
            Thread.onSpinWait();
        }

        try {
            pokemon.setFirstPokemonImageUrl(response1.get());
            pokemon.setSecondPokemonImageUrl(response2.get());
            pokemon.setFirstPokemonType(response3.get());
            pokemon.setSecondPokemonType(response4.get());
            pokemon.setFirstName(StringUtils.capitalize(pokemon.getFirstName()));
            pokemon.setSecondName(StringUtils.capitalize(pokemon.getSecondName()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return "error";
        }

        PokemonType firstPokemonType = pokemon.getFirstPokemonType();
        PokemonType secondPokemonType = pokemon.getSecondPokemonType();
        Future<TypeData> typeDataFuture = getTypeData(secondPokemonType.getTypes().get(0));

        while (!typeDataFuture.isDone()) {
            Thread.onSpinWait();
        }

        try {
            TypeData typeData = typeDataFuture.get();
            FightReferee fightReferee = new FightReferee();
            String resultMessage = fightReferee.getResultMessage(firstName, secondName, firstPokemonType, typeData);
            pokemon.setResultMessage(resultMessage);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return "error";
        }
        return "result";
    }
}
