package io.hacksy.aoc.v2019.day00;

import io.hacksy.aoc.util.FileUtil;
import io.hacksy.aoc.util.Performance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Day00App {
    public static void main(String[] args) {
        Day00Processor processor = new Day00Processor();
        File file = FileUtil.getResourceFile("input/day00/Day00.txt");
        Performance.timeAndPrint(() -> "Test Run: " + processor.proccess(file));
    }
}
