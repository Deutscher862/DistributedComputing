package com.agh.lab2_pokemonrestapp.model;

public class PokemonImageUrl extends RequestData {
    Sprites sprites;

    public String getImageUrl() {
        return sprites.getFront_default();
    }

    public void setNullSprites() {
        sprites = new Sprites();
        sprites.front_default = "";
    }

    static class Sprites {
        String front_default;

        public String getFront_default() {
            return front_default;
        }
    }
}
