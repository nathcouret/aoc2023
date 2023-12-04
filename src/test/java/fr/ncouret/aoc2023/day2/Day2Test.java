package fr.ncouret.aoc2023.day2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import fr.ncouret.aoc2023.TestUtils;

class Day2Test {

    private static final Path DAY2 = TestUtils.TEST_RESOURCE_PATH.resolve("day2");

    @Test
    void testStar1() {

        assertEquals(8, Day2.star1(DAY2.resolve("sample.txt").toString()));
    }

    @Test
    void testStar2() {
        assertEquals(2286, Day2.star2(DAY2.resolve("sample2.txt").toString()));
    }

}