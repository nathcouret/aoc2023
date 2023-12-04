package fr.ncouret.aoc2023.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.IntBinaryOperator;
import java.util.function.ToIntFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ncouret.aoc2023.Utils;

public class Day1 {


    private static final Path DAY1 = Utils.RESOURCES_PATH.resolve("day1");
    private static final Logger LOGGER = LoggerFactory.getLogger(Day1.class);

    private static final Map<String, String> DIGITS = Map.of(
            "zero", "0",
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
    );

    public static void main(String[] args) {
        var dataPath = DAY1.resolve("data.txt").toString();
        var star1 = star1(dataPath);
        var star2 = star2(dataPath);
        LOGGER.info("Star 1: {}", star1);
        LOGGER.info("Star 2: {}", star2);
    }

    public static int star1(String inputPath) {
        return processData(inputPath, Day1::star1Process, Integer::sum);
    }

    public static int star2(String inputPath) {
        return processData(inputPath, Day1::star2Process, Integer::sum);
    }

    private static int processData(String inputPath, ToIntFunction<String> processor, IntBinaryOperator accumulator) {
        try (var reader = new BufferedReader(new FileReader(inputPath))) {
            return reader.lines()
                    .mapToInt(processor)
                    .peek(i -> LOGGER.debug("{}", i))
                    .reduce(accumulator)
                    .orElse(-1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int star1Process(String line) {
        var digits = line.chars()
                .filter(Character::isDigit)
                .toArray();
        if (digits.length == 0) {
            return 0;
        }
        return Integer.parseInt(Character.toString(digits[0]) + Character.toString(digits[digits.length - 1]));
    }

    public static int star2Process(String line) {
        String copy = line;
        List<String> digits = new ArrayList<>();
        while (!copy.isBlank()) {
            LOGGER.debug("{}", copy);
            LOGGER.debug("{}", digits);
            int advance = 1;
            if (Character.isDigit(copy.charAt(0))) {
                digits.add(String.valueOf(copy.charAt(0)));
            } else {
                for (var key : DIGITS.keySet()) {
                    if (copy.startsWith(key)) {
                        digits.add(DIGITS.get(key));
                        advance = key.length() - 1;
                        break;
                    }
                }
            }
            copy = copy.substring(advance);
        }
        if (digits.isEmpty()) {
            return 0;
        }
        return Integer.parseInt((digits.getFirst() + digits.getLast()));
    }


}
