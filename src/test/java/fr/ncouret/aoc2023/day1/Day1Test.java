package fr.ncouret.aoc2023.day1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {

    private static final Path DAY1 = Paths.get("src", "test", "resources", "day1").toAbsolutePath();

    @Test
    void testStar1() {
        assertEquals(142, Day1.star1(DAY1.resolve("sample.txt").toString()));
    }

    @Test
    void testStar2() {
        assertEquals(281, Day1.star2(DAY1.resolve("sample2.txt").toString()));
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            29,two1nine
            83,eightwothree
            13,abcone2threexyz
            24,xtwone3four
            42,4nineeightseven2
            14,zoneight234
            76,7pqrstsixteen
            """)
    void testConvertLine(int expected, String line) {
        assertEquals(expected, Day1.star2Process(line));
    }

}