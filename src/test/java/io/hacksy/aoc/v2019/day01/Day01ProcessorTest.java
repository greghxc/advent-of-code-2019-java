package io.hacksy.aoc.v2019.day01;

import io.hacksy.aoc.util.FileUtil;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day01ProcessorTest {
    private Day01Processor processor = new Day01Processor();

    @Test
    void partOne() {
        var massList = List.of(12, 14, 1969, 100756);
        var expectedResults = List.of(2, 2, 654, 33583);

        var actualResults = massList.map(i -> processor.partOne(List.of(i)));

        assertThat(actualResults, is(expectedResults));
    }

    @Test
    void partTwo() {
        var massList = List.of(14, 1969, 100756);
        var expectedResults = List.of(2, 966, 50346);

        var actualResults = massList.map(i -> processor.partTwo(List.of(i)));

        assertThat(actualResults, is(expectedResults));
    }
}