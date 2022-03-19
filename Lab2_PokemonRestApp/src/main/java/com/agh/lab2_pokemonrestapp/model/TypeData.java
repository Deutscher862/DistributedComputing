package com.agh.lab2_pokemonrestapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TypeData extends RequestData {
    DamageRelations damage_relations;

    public List<String> getTypesThatCountYou() {
        List<TypeName> typeNames = new ArrayList<>(damage_relations.double_damage_from);
        typeNames.addAll(damage_relations.half_damage_to);
        typeNames.addAll(damage_relations.no_damage_to);

        return getStringTypeNames(typeNames);
    }

    public List<String> getTypesThatYouCount() {
        List<TypeName> typeNames = new ArrayList<>(damage_relations.double_damage_to);
        typeNames.addAll(damage_relations.half_damage_from);
        typeNames.addAll(damage_relations.no_damage_from);

        return getStringTypeNames(typeNames);
    }

    private List<String> getStringTypeNames(List<TypeName> typeNames) {
        return typeNames.stream()
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
}
