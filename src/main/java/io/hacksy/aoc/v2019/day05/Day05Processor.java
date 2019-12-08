package io.hacksy.aoc.v2019.day05;

import io.hacksy.aoc.v2019.intcomputer.IntComputer;
import io.vavr.collection.List;

public class Day05Processor {
    int partOne(List<Integer> program) {
        var computer = new IntComputer(program, List.of(1));
        return computer.run().last();
    }

    int partTwo(List<Integer> program) {
        var computer = new IntComputer(program, List.of(5));
        return computer.run().last();
    }
}
