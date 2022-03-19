package com.agh.lab2_pokemonrestapp.model;

import java.util.List;

public class PokemonType {
    List<Data> data;

    public List<String> getTypes() {
        return data.get(0).types;
    }

    static class Data {
        public List<String> types;
    }
}
