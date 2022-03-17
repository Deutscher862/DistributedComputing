package com.agh.lab2_pokemonrestapp;

import com.agh.lab2_pokemonrestapp.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class PokemonController {

    @Autowired
    private final PokemonService pokemonService;

    PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @PostMapping("/pokemon")
    public String greetingSubmit(@ModelAttribute Pokemon pokemon, Model model) {
        model.addAttribute("pokemon", pokemon);
        System.out.println(pokemon.getFirstName());
        System.out.println(pokemon.getSecondName());
        pokemonService.getParameters(pokemon);
        return "result";
    }
}
