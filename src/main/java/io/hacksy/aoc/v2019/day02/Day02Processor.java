package io.hacksy.aoc.v2019.day02;

import io.vavr.collection.List;

class Day02Processor {
    String partOne(String input) {
        var ints = List.of(input.split(",")).map(Integer::parseInt);
        return Integer.toString(doCalc(ints));
    }

    String partTwo(String input) {
        var products = List.range(0, 100).crossProduct();
        while (products.hasNext()) {
            var ints = List.of(input.split(",")).map(Integer::parseInt);
            var p = products.next();
            ints = replaceIndex(ints, 1, p._1());
            ints = replaceIndex(ints, 2, p._2());
            if (doCalc(ints) == 19690720) {
                return Integer.toString(100 * p._1() + p._2());
            }
        }
        throw new RuntimeException("None found.");
    }

    private int doCalc(List<Integer> ints) {
        for (int i = 0; i < ints.size(); i += 4) {
            switch (ints.get(i)) {
                case 1:
                    ints = replaceIndex(ints, ints.get(i + 3), ints.get(ints.get(i + 1)) + ints.get(ints.get(i + 2)));
                    break;
                case 2:
                    ints = replaceIndex(ints, ints.get(i + 3), ints.get(ints.get(i + 1)) * ints.get(ints.get(i + 2)));
                    break;
                case 99:
                    return ints.get(0);
                default:
                    throw new RuntimeException("Unexpected opscode Input");
            }
        }
        throw new RuntimeException("Does not terminate.");
    }

    private List<Integer> replaceIndex(List<Integer> integers, int index, int value) {
        return integers.subSequence(0, index).append(value).appendAll(integers.subSequence(index + 1));
    }
}