package com.agh.lab2_pokemonrestapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TypeData {
    DamageRelations damage_relations;

    //TODO DRY

    public List<String> getTypesThatCountYou() {
        List<TypeName> newList = new ArrayList<>(damage_relations.double_damage_from);
        newList.addAll(damage_relations.half_damage_to);
        newList.addAll(damage_relations.no_damage_to);

        return newList.stream()
                .map(TypeName::getName)
                .collect(Collectors.toList());
    }

    public List<String> getTypesThatYouCount() {
        List<TypeName> newList = new ArrayList<>(damage_relations.double_damage_to);
        newList.addAll(damage_relations.half_damage_from);
        newList.addAll(damage_relations.no_damage_from);

        return newList.stream()
                .map(TypeName::getName)
                .collect(Collectors.toList());
    }

    static class DamageRelations {
        List<TypeName> double_damage_from;
        List<TypeName> double_damage_to;
        List<TypeName> half_damage_from;
        List<TypeName> half_damage_to;
        List<TypeName> no_damage_from;
        List<TypeName> no_damage_to;
    }

    static class TypeName {
        String name;

        public String getName() {
            return name;
        }
    }

    @Override
    public String toString() {
        return "TypeData{" +
                "damage_relations=" + damage_relations +
                '}';
    }
}
