package fr.ncouret.aoc2023.day4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public record ScratchCard(
    int id,
    List<Integer> winningNumbers,
    List<Integer> scratchedNumbers
) {

    static ScratchCard parse(String input) {
        String[] split = input.split(":");
        int id = Integer.parseInt(split[0].split("\\s+")[1].trim());
        String[] numbers = split[1].split("\\|");
        List<Integer> winningNumbers = Arrays.stream(numbers[0].trim().split("\\s+"))
            .map(String::trim)
            .mapToInt(Integer::parseInt)
            .boxed()
            .toList();
        List<Integer> scratchedNumbers = Arrays.stream(numbers[1].trim().split("\\s+"))
            .map(String::trim)
            .mapToInt(Integer::parseInt)
            .boxed()
            .toList();
        return new ScratchCard(id, winningNumbers, scratchedNumbers);
    }

    public List<Integer> scratchedWinners() {
        return scratchedNumbers.stream()
            .filter(winningNumbers::contains)
            .toList();
    }

    public int points() {
        List<Integer> winners = scratchedWinners();
        if (winners.isEmpty()) {
            return 0;
        }
        if (winners.size() == 1) {
            return 1;
        }
        return IntStream.range(1, winners.size())
            .map(i -> 2)
            .reduce(1, Math::multiplyExact);
    }

    public int winnersCount() {
        return (int) scratchedNumbers.stream()
            .filter(winningNumbers::contains)
            .count();
    }

    @Override
    public String toString() {
        return "{\n"
            + "\tid: " + id + ",\n"
            + "\t: w: " + winningNumbers + ",\n"
            + "\t: n: " + scratchedNumbers + ",\n"
            + "}";
    }
}
