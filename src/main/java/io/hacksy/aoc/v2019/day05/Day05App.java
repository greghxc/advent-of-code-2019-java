package io.hacksy.aoc.v2019.day05;

import io.hacksy.aoc.util.FileUtil;
import io.hacksy.aoc.util.Performance;
import io.vavr.collection.List;

import java.io.File;

public class Day05App {
    public static void main(String[] args) {
        Day05Processor processor = new Day05Processor();

        File file = FileUtil.getResourceFile("input/day05/day05.txt");

        var input = FileUtil.fileToStringList(file).head();
        var program = List.of(input.split(",")).map(Integer::parseInt);

        Performance.timeAndPrint(() ->
                String.format("Day 4 - Part 1: %s", processor.partOne(program)));
        Performance.timeAndPrint(() ->
                String.format("Day 4 - Part 2: %s", processor.partTwo(program)));
    }
}
