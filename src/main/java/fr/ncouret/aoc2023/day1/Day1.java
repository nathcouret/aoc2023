package fr.ncouret.aoc2023.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.IntBinaryOperator;
import java.util.function.ToIntFunction;

public class Day1 {


    public static final Path RESOURCES = Paths.get("src", "main", "resources").toAbsolutePath();

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
        var dataPath = RESOURCES.resolve(Paths.get("day1", "data.txt")).toString();
        System.out.println("toto:" + dataPath);
        var star1 = star1(dataPath);
        var star2 = star2(dataPath);
        System.out.println(star1);
        System.out.println(star2);
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
                    .peek(System.out::println)
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
            //System.out.println(copy);
            //System.out.println(digits);
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
