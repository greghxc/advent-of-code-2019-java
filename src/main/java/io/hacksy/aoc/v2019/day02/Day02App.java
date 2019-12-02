package io.hacksy.aoc.v2019.day02;

import io.hacksy.aoc.util.FileUtil;
import io.hacksy.aoc.util.Performance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Day02App {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Day02App.class);
        Day02Processor processor = new Day02Processor();

        File file = FileUtil.getResourceFile("input/day02/Day02.txt");

        Performance.timeAndPrint(() ->
                String.format("Day 2 - Part 1: %s", processor.partOne(FileUtil.fileToStringList(file).get(0))));
        Performance.timeAndPrint(() ->
                String.format("Day 2 - Part 2: %s", processor.partTwo(FileUtil.fileToStringList(file).get(0))));
    }
}
