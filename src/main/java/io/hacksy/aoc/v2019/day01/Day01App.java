package io.hacksy.aoc.v2019.day01;

import io.hacksy.aoc.util.FileUtil;
import io.hacksy.aoc.util.Performance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Day01App {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Day01App.class);
        Day01Processor processor = new Day01Processor();

        File file = FileUtil.getResourceFile("input/day01/Day01.txt");

        Performance.timeAndPrint(() ->
                String.format("Day 1 - Part 1: %s", processor.partOne(FileUtil.fileToList(file))));
        Performance.timeAndPrint(() ->
                String.format("Day 1 - Part 2: %s", processor.partTwo(FileUtil.fileToList(file))));
    }
}
