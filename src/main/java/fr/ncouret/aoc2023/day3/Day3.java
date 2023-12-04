package fr.ncouret.aoc2023.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ncouret.aoc2023.Utils;

public class Day3 {

    private static final Path DAY3 = Utils.RESOURCES_PATH.resolve("day3");
    private static final Logger LOGGER = LoggerFactory.getLogger(Day3.class);

    public static void main(String[] args) {
        String input = DAY3.resolve("data.txt").toString();

        int star1 = star1(input);
        int star2 = star2(input);
        LOGGER.info("Star 1: {}", star1);
        LOGGER.info("Star 2: {}", star2);
    }

    public static int star1(String inputPath) {
        try (var reader = new BufferedReader(new FileReader(inputPath))) {
            Matrix matrix = new Matrix(reader.lines().toList());
            return matrix.numberMatrices()
                .stream()
                .filter(Matrix::containsSymbol)
                .map(m -> m.numberAt(1, 1))
                .mapToInt(Integer::parseInt)
                .sum();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int star2(String inputPath) {
        try (var reader = new BufferedReader(new FileReader(inputPath))) {
            Matrix matrix = new Matrix(reader.lines().toList());
            var numbers = matrix.numberMatrices();
            List<SubMatrix> gears = matrix.allForCharacter('*');
            return gears.stream()
                .map(findNeighbouringParts(numbers))
                .filter(Day3::excludeSingleParts)
                .mapToInt(calculateGearRatio())
                .sum();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean excludeSingleParts(int[] parts) {
        return parts.length > 1;
    }

    private static ToIntFunction<int[]> calculateGearRatio() {
        return parts -> Arrays.stream(parts).reduce(Math::multiplyExact).orElse(0);
    }

    private static Function<SubMatrix, int[]> findNeighbouringParts(List<SubMatrix> numbers) {
        return gear -> numbers.stream()
            .filter(m -> m.containsIndex(gear.getRowOffset(), gear.getColOffset()))
            .map(m -> m.numberAt(1, 1))
            .mapToInt(Integer::parseInt).toArray();
    }


}
