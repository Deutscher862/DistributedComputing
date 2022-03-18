package com.agh.lab2_pokemonrestapp.model;

public class Pokemon {
    private String firstName;
    private String SecondName;
    private PokemonData firstPokemonData;
    private PokemonData SecondPokemonData;
    private String resultMessage;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return SecondName;
    }

    public void setSecondName(String secondName) {
        SecondName = secondName;
    }

    public PokemonData getFirstPokemonData() {
        return firstPokemonData;
    }

    public void setFirstPokemonData(PokemonData firstPokemonData) {
        this.firstPokemonData = firstPokemonData;
    }

    public PokemonData getSecondPokemonData() {
        return SecondPokemonData;
    }

    public void setSecondPokemonData(PokemonData secondPokemonData) {
        SecondPokemonData = secondPokemonData;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
