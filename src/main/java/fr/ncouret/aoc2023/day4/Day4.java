package fr.ncouret.aoc2023.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ncouret.aoc2023.Utils;

public class Day4 {

    private static final Path DAY4 = Utils.RESOURCES_PATH.resolve("day4");
    private static final Logger LOGGER = LoggerFactory.getLogger(Day4.class);

    public static void main(String[] args) {
        String input = DAY4.resolve("data.txt").toString();

        int star1 = star1(input);
        int star2 = star2(input);
        LOGGER.info("Star 1: {}", star1);
        LOGGER.info("Star 2: {}", star2);
    }

    public static int star1(String inputPath) {
        try (var reader = new BufferedReader(new FileReader(inputPath))) {
            return reader.lines()
                .map(ScratchCard::parse)
                .mapToInt(ScratchCard::points)
                .reduce(Integer::sum)
                .orElse(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int star2(String inputPath) {
        try (var reader = new BufferedReader(new FileReader(inputPath))) {
            List<ScratchCard> cards = reader.lines()
                .map(ScratchCard::parse)
                .toList();

            Map<Integer, AtomicInteger> cardsWon = new HashMap<>();
            for (ScratchCard card : cards) {
                cardsWon.put(card.id(), new AtomicInteger(1));
            }
            int numberOfCards = cards.size();
            for (int i = 0; i < numberOfCards; i++) {
                ScratchCard card = cards.get(i);
                int numberOfCopies = cardsWon.get(card.id()).getPlain();
                int winsCount = card.winnersCount();
                for (int j = i + 1; j <= i + winsCount && j < numberOfCards; j++) {
                    cardsWon.get(j + 1).addAndGet(numberOfCopies);
                }
            }

            return cardsWon.values()
                .stream()
                .mapToInt(AtomicInteger::getPlain)
                .sum();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
