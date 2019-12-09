package io.hacksy.aoc.v2019.day07;

import io.hacksy.aoc.v2019.intcomputer.IntComputer;
import io.vavr.collection.List;

class Day07Processor {
    long partOne(List<Long> program) {
        return List.of(0,1,2,3,4)
                .map(Long::valueOf)
                .permutations()
                .map(p -> runPhaseSettings(program, p))
                .max().getOrNull();
    }

    long partTwo(List<Long> program) {
        return List.of(5,6,7,8,9)
                .map(Long::valueOf)
                .permutations()
                .map(p -> runPhaseSettings(program, p))
                .max().getOrNull();
    }

    long runPhaseSettings(List<Long> program, List<Long> phaseSettings) {
        var amps = List.of(
                new IntComputer(program, List.of(phaseSettings.get(0))),
                new IntComputer(program, List.of(phaseSettings.get(1))),
                new IntComputer(program, List.of(phaseSettings.get(2))),
                new IntComputer(program, List.of(phaseSettings.get(3))),
                new IntComputer(program, List.of(phaseSettings.get(4)))
        );

        var input = 0L;

        while (amps.find(c -> c.getStatus() == IntComputer.Status.TERMINATED).isEmpty()) {
            for (IntComputer amp : amps) {
                input = amp.addInput(input).run().last();
            }
        }

        return input;
    }
}
