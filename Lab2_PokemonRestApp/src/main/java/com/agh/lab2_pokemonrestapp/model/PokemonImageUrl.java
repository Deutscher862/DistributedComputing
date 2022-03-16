package com.agh.lab2_pokemonrestapp.model;

public class PokemonImageUrl {
    public Sprites sprites;

    public String getImageUrl() {
        return sprites.getFront_default();
    }

    public static class Sprites {
        String front_default;

        public String getFront_default() {
            return front_default;
        }
    }
}
