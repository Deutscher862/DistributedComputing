package com.agh.lab2_pokemonrestapp;

import java.util.Map;

public class Commons {
    public final static String POKEAPI_POKEMON_DATA = "https://pokeapi.co/api/v2/pokemon/";
    public final static String POKEAPI_TYPE_DATA = "https://pokeapi.co/api/v2/type/";
    public final static String POKEMONTCG_POKEMON_TYPE = "https://api.pokemontcg.io/v2/cards?q=name:";
    public final static Map<String, String> TYPE_ID_MAP = Map.ofEntries(
            Map.entry("Normal", "1"),
            Map.entry("Fighting", "2"),
            Map.entry("Flying", "3"),
            Map.entry("Poison", "4"),
            Map.entry("Ground", "5"),
            Map.entry("Rock", "6"),
            Map.entry("Bug", "7"),
            Map.entry("Ghost", "8"),
            Map.entry("Steel", "9"),
            Map.entry("Fire", "10"),
            Map.entry("Water", "11"),
            Map.entry("Grass", "12"),
            Map.entry("Electric", "13"),
            Map.entry("Psychic", "14"),
            Map.entry("Ice", "15"),
            Map.entry("Dragon", "16"),
            Map.entry("Dark", "17"),
            Map.entry("Fairy", "18")
    );
}
