package io.hacksy.aoc.v2019.day09;

import io.hacksy.aoc.v2019.intcomputer.IntComputer;
import io.vavr.collection.List;

public class Day09Processor {
    long partOne(List<Long> program) {
        var computer = new IntComputer(program, List.of(1L));
        return computer.run().last();
    }
    long partTwo(List<Long> program) {
        var computer = new IntComputer(program, List.of(2L));
        return computer.run().last();
    }
}
