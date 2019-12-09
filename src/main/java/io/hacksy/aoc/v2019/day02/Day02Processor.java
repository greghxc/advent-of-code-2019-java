package io.hacksy.aoc.v2019.day02;

import io.hacksy.aoc.v2019.intcomputer.IntComputer;
import io.vavr.collection.List;

class Day02Processor {
    String partOne(String input) {
        var ints = List.of(input.split(",")).map(Long::parseLong);
        ints = ints.update(1, 12L);
        ints = ints.update(2, 2L);

        var computer = new IntComputer(ints, List.empty());
        computer.run();

        return Long.toString(computer.dumpMemory().head());
    }

    String partTwo(String input) {
        var products = List.range(0L, 100L).crossProduct();
        while (products.hasNext()) {
            var p = products.next();
            var ints = List.of(input.split(",")).map(Long::parseLong);
            ints = ints.update(1, p._1());
            ints = ints.update(2, p._2());

            var computer = new IntComputer(ints, List.empty());
            computer.run();

            var memoryDump = computer.dumpMemory();

            if (memoryDump.head() == 19690720) {
                return Long.toString(100 * memoryDump.get(1) + memoryDump.get(2));
            }
        }
        throw new RuntimeException("None found.");
    }
}