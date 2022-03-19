package com.agh.lab2_pokemonrestapp;

import java.util.Map;

public class Commons {
    public final static String POKEAPI_POKEMON_DATA = "https://pokeapi.co/api/v2/pokemon/";
    public final static String POKEAPI_TYPE_DATA = "https://pokeapi.co/api/v2/type/";
    public final static String POKEMONTCG_POKEMON_TYPE = "https://api.pokemontcg.io/v2/cards?q=name:";
    public final static Map<String, String> TYPE_ID_MAP = Map.ofEntries(
            Map.entry("normal", "1"),
            Map.entry("fighting", "2"),
            Map.entry("flying", "3"),
            Map.entry("poison", "4"),
            Map.entry("ground", "5"),
            Map.entry("rock", "6"),
            Map.entry("bug", "7"),
            Map.entry("ghost", "8"),
            Map.entry("steel", "9"),
            Map.entry("fire", "10"),
            Map.entry("water", "11"),
            Map.entry("grass", "12"),
            Map.entry("electric", "13"),
            Map.entry("psychic", "14"),
            Map.entry("ice", "15"),
            Map.entry("dragon", "16"),
            Map.entry("dark", "17"),
            Map.entry("fairy", "18")
    );
}
