package com.agh.lab2_pokemonrestapp.datafinder;

import java.io.IOException;

interface DataFinder {
    String findData(String name) throws IOException;
}
