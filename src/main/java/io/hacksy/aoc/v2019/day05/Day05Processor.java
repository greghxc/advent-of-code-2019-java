package io.hacksy.aoc.v2019.day05;

import io.hacksy.aoc.v2019.intcomputer.IntComputer;
import io.vavr.collection.List;

public class Day05Processor {
    long partOne(List<Integer> program) {
        var computer = new IntComputer(program.map(Long::valueOf), List.of(1L));
        return computer.run().last();
    }

    long partTwo(List<Integer> program) {
        var computer = new IntComputer(program.map(Long::valueOf), List.of(5L));
        return computer.run().last();
    }
}
