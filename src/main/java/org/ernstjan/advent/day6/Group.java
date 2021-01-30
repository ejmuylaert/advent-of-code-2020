package org.ernstjan.advent.day6;

import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Group {

    LinkedList<Set<Character>> forms = new LinkedList<>();

    void addForm(Set<Character> form) {
        forms.add(form);
    }

    int allYes() {
        Set<Character> uniqueQuestions = forms
                .stream()
                .reduce(new HashSet<>(), (left, right) -> {
                    // Create a copy, so the original data won't change
                    HashSet<Character> result = new HashSet<>(left);
                    result.addAll(right);
                    return result;
                });

        return uniqueQuestions.size();
    }

    int commonYes() {
        Set<Character> allYes = Flux.fromIterable(forms)
                .reduce((left, right) -> {
                    // Create a copy, so the original data won't change
                    HashSet<Character> result = new HashSet<>(left);
                    result.retainAll(right);
                    return result;
                }).block();

        return allYes.size();
    }
}
