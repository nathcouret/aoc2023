package fr.ncouret.aoc2023.day2;

import java.util.Arrays;
import java.util.List;

public record Game(List<Draw> draws, int number) {

    static Game parse(String gameString) {
        var split = gameString.split(":");
        int number = Integer.parseInt(split[0].substring(5));
        var draws = Arrays.stream(split[1].split(";"))
                .map(Draw::parse)
                .toList();
        return new Game(draws, number);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{ g: ")
                .append(number)
                .append("\n");
        for(var draw : draws) {
            builder.append("\t").append(draw).append("\n");
        }
        builder.append("}");
        return builder.toString();
    }
}
