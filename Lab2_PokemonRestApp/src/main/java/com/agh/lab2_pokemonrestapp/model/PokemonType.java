package com.agh.lab2_pokemonrestapp.model;

import java.beans.Introspector;
import java.util.List;

public class PokemonType extends RequestData {
    List<Data> data;

    public List<String> getTypes() {
        return data.get(0).types;
    }

    public void decapitalizeNames() {
        List<String> types = getTypes();
        for (int i = 0; i < types.size(); i++) {
            if (types.get(i).equals("Lightning")) {
                types.set(i, "electric");
                return;
            } else {
                types.set(i, Introspector.decapitalize(types.get(i)));
            }
        }
    }

    static class Data {
        public List<String> types;
    }
}
