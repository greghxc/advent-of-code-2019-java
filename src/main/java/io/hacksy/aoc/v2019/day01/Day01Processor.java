package io.hacksy.aoc.v2019.day01;

import io.vavr.collection.List;

class Day01Processor {
    int partOne(List<Integer> integers) {
        return integers
                .map(this::fuelForMass)
                .sum().intValue();
    }

    int partTwo(List<Integer> integers) {
        return integers
                .map(i -> this.fuelForMassRecursive(0, i))
                .sum().intValue();
    }

    private int fuelForMassRecursive(int total, int mass) {
        var fuel = fuelForMass(mass);
        if (fuel <= 0) { return total; }
        return fuelForMassRecursive(total + fuel, fuel);
    }

    private int fuelForMass(int mass) {
        return mass / 3 - 2;
    }
}
