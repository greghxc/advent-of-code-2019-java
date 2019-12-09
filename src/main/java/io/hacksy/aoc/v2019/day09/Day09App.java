package io.hacksy.aoc.v2019.day09;

import io.hacksy.aoc.util.FileUtil;
import io.hacksy.aoc.util.Performance;
import io.vavr.collection.List;

import java.io.File;

public class Day09App {
    public static void main(String[] args) {
        Day09Processor processor = new Day09Processor();

        File file = FileUtil.getResourceFile("input/day09/day09.txt");

        var input = FileUtil.fileToStringList(file).head();
        var program = List.of(input.split(",")).map(Long::parseLong);

        Performance.timeAndPrint(() ->
                String.format("Day 7 - Part 1: %s", processor.partOne(program)));
        Performance.timeAndPrint(() ->
                String.format("Day 7 - Part 2: %s", processor.partTwo(program)));
    }
}
