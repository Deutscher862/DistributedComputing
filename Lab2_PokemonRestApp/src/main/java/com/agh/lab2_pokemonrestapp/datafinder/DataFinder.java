package com.agh.lab2_pokemonrestapp.datafinder;

import com.agh.lab2_pokemonrestapp.model.RequestData;

import java.io.IOException;

interface DataFinder {
    RequestData findData(String name) throws IOException;
}
