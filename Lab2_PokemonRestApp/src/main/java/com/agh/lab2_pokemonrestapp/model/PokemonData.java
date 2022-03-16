package com.agh.lab2_pokemonrestapp.model;

import java.util.ArrayList;
import java.util.List;

public class PokemonData {
    Sprites sprites;
    List<Type> types;

    public String getImageUrl() {
        return sprites.getFront_default();
    }

    public List<String> getTypes() {
        List<String> result = new ArrayList<>();
        for (Type type : types) {
            result.add(type.type.name);
        }
        return result;
    }

    static class Sprites {
        String front_default;

        public String getFront_default() {
            return front_default;
        }
    }

    static class Type {
        PokemonType type;
    }

    static class PokemonType {
        String name;
    }
}
