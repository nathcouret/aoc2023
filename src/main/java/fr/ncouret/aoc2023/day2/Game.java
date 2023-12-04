package fr.ncouret.aoc2023.day2;

import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntFunction;

public record Game(List<Draw> draws, int number) {

    static Game parse(String gameString) {
        var split = gameString.split(":");
        int number = Integer.parseInt(split[0].substring(5));
        var draws = Arrays.stream(split[1].split(";"))
                .map(Draw::parse)
                .toList();
        return new Game(draws, number);
    }

    public int maxReds() {
        return maxOfColor(Draw::reds);
    }

    public int maxGreens() {
        return maxOfColor(Draw::greens);
    }

    public int maxBlues() {
        return maxOfColor(Draw::blues);
    }
    public int power() {
        return maxReds() * maxGreens() * maxBlues();
    }

    private int maxOfColor(ToIntFunction<Draw> colorGetter) {
        return draws.stream()
            .mapToInt(colorGetter)
            .max()
            .orElse(0);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{")
            .append("\tg: ").append(number).append("\n");
        for(var draw : draws) {
            builder.append("\t").append(draw).append("\n");
        }
        builder.append("\t max: {\n")
                .append("\t\tr: ").append(maxReds()).append("\n")
                .append("\t\tg: ").append(maxGreens()).append("\n")
                .append("\t\tb: ").append(maxBlues()).append("\n")
                .append("\t}\n");
        builder.append("\t pow: ").append(power()).append("\n");
        builder.append("}");
        return builder.toString();
    }
}
