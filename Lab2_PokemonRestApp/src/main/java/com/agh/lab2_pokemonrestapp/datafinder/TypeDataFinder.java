package com.agh.lab2_pokemonrestapp.datafinder;

import com.agh.lab2_pokemonrestapp.Commons;
import com.agh.lab2_pokemonrestapp.datafinder.request.RequestMaker;
import com.agh.lab2_pokemonrestapp.model.TypeData;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;

public class TypeDataFinder {
    public TypeData findData(String typeName) throws IOException {
        String typeId = Commons.TYPE_ID_MAP.get(typeName);
        URL url = new URL(Commons.POKEAPI_TYPE_DATA + typeId);
        RequestMaker requestMaker = new RequestMaker();
        String json = requestMaker.sendGetRequest(url);
        TypeData typeData = new Gson().fromJson(json, TypeData.class);
        System.out.println(typeData);
        return typeData;
    }
}
