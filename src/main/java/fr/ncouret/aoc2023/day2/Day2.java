package fr.ncouret.aoc2023.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;
import java.util.function.ToIntFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ncouret.aoc2023.Utils;

public class Day2 {
    public static final Path RESOURCES = Utils.RESOURCES_PATH.resolve("day2");
    private static final Logger LOGGER = LoggerFactory.getLogger(Day2.class);

    public static void main(String[] args) {
        var dataPath = RESOURCES.resolve("data.txt").toString();

        int star1 = star1(dataPath);
        int star2 = star2(dataPath);
        LOGGER.info("Star 1: {}", star1);
        LOGGER.info("Star 2: {}", star2);
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
                .peek(i -> LOGGER.debug(String.valueOf(i)))
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
        LOGGER.trace(game.toString());
        LOGGER.debug("power: {}", game.power());
        return game.power();
    }
}
