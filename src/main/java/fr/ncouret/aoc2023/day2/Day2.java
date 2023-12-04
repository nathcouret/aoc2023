package fr.ncouret.aoc2023.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;
import java.util.function.ToIntFunction;

import fr.ncouret.aoc2023.Utils;

public class Day2 {

    public static final Path RESOURCES = Utils.RESOURCES_PATH.resolve("day2");

    public static void main(String[] args) {
        var dataPath = RESOURCES.resolve("data.txt").toString();

        int star1 = star1(dataPath);
        int star2 = star2(dataPath);
        System.out.println(star1);
        System.out.println(star2);
    }

    public static int star1(String inputPath) {
        return processData(inputPath, Day2::star1Process, Integer::sum, i -> i > 0);
    }

    public static int star2(String inputPath) {
        return processData(inputPath, Day2::star2Process, Integer::sum, i -> i > 0);
    }

    private static int processData(String inputPath, ToIntFunction<String> processor, IntBinaryOperator accumulator, IntPredicate filter) {
        try (var reader = new BufferedReader(new FileReader(inputPath))) {
            return reader.lines()
                .mapToInt(processor)
                .filter(filter)
                .peek(System.out::println)
                .reduce(accumulator)
                .orElse(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int star1Process(String gameInput) {
        Game game = Game.parse(gameInput);
        if (game.maxReds() > 12 || game.maxBlues() > 14 || game.maxGreens() > 13) {
            return 0;
        }
        return game.number();
    }

    private static int star2Process(String gameInput) {
        Game game = Game.parse(gameInput);
        //System.out.println(game);
        System.out.println("power: " + game.power());
        return game.power();
    }
}
