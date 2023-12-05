package fr.ncouret.aoc2023.day4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import fr.ncouret.aoc2023.TestUtils;

class Day4Test {

    private static final Path DAY4 = TestUtils.TEST_RESOURCE_PATH.resolve("day4");

    @Test
    void testStar1() {
        var star1 = Day4.star1(DAY4.resolve("sample.txt").toString());
        assertEquals(13, star1);
    }

    @Test
    void testStar2() {
        var star1 = Day4.star2(DAY4.resolve("sample.txt").toString());
        assertEquals(30, star1);
    }

}