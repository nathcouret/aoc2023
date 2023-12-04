package fr.ncouret.aoc2023.day3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import fr.ncouret.aoc2023.TestUtils;

class Day3Test {

    private static final Path DAY3 = TestUtils.TEST_RESOURCE_PATH.resolve("day3");

    @Test
    void testStar1() {
        var star1 = Day3.star1(DAY3.resolve("sample.txt").toString());
        assertEquals(4361, star1);
    }

    @Test
    void testStar2() {
        var star2 = Day3.star2(DAY3.resolve("sample.txt").toString());
        assertEquals(467835, star2);
    }

}