package fr.ncouret.aoc2023.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.IntBinaryOperator;
import java.util.function.ToIntFunction;

public class Day2 {

    public static final Path RESOURCES = Paths.get("src", "main", "resources").toAbsolutePath();

    public static void main(String[] args) {
        var dataPath = RESOURCES.resolve(Paths.get("day2", "data.txt")).toString();

        try (var reader = new BufferedReader(new FileReader(dataPath))) {
            var toto = reader.lines()
                    .peek(System.out::println)
                    .map(Game::parse)
                    .peek(System.out::println)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
}
