package io.hacksy.aoc.v2019.day08;

import io.hacksy.aoc.util.FileUtil;
import io.hacksy.aoc.util.Performance;

import java.io.File;

public class Day08App {
    public static void main(String[] args) {
        Day08Processor processor = new Day08Processor();

        File file = FileUtil.getResourceFile("input/day08/day08.txt");

        var input = FileUtil.fileToStringList(file).head();

        Performance.timeAndPrint(() ->
            String.format("Day 8 - Part 1: %s", processor.partOne(25, 6, input)));
        Performance.timeAndPrint(() -> {
            processor.partTwo(25, 6, input, "test.gif");
            return "Day 8 - Part 1: Complete";
        });
    }
}
