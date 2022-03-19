package com.agh.lab2_pokemonrestapp.model;

public class Pokemon {
    private String firstName;
    private String SecondName;
    private PokemonImageUrl firstPokemonImageUrl;
    private PokemonImageUrl SecondPokemonImageUrl;
    private PokemonType firstPokemonType;
    private PokemonType secondPokemonType;
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

    public PokemonImageUrl getFirstPokemonImageUrl() {
        return firstPokemonImageUrl;
    }

    public void setFirstPokemonImageUrl(PokemonImageUrl firstPokemonImageUrl) {
        this.firstPokemonImageUrl = firstPokemonImageUrl;
    }

    public PokemonImageUrl getSecondPokemonImageUrl() {
        return SecondPokemonImageUrl;
    }

    public void setSecondPokemonImageUrl(PokemonImageUrl secondPokemonImageUrl) {
        SecondPokemonImageUrl = secondPokemonImageUrl;
    }

    public PokemonType getFirstPokemonType() {
        return firstPokemonType;
    }

    public void setFirstPokemonType(PokemonType firstPokemonType) {
        this.firstPokemonType = firstPokemonType;
    }

    public PokemonType getSecondPokemonType() {
        return secondPokemonType;
    }

    public void setSecondPokemonType(PokemonType secondPokemonType) {
        this.secondPokemonType = secondPokemonType;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
