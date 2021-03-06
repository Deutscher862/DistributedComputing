package com.agh.lab2_pokemonrestapp;

import com.agh.lab2_pokemonrestapp.model.Pokemon;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class PokemonController {

    private final PokemonService pokemonService;

    PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @PostMapping("/pokemon")
    public String pokemonSubmit(@ModelAttribute Pokemon pokemon, Model model) {
        model.addAttribute("pokemon", pokemon);
        return pokemonService.makeRequestsAndGetResult(pokemon);
    }
}
