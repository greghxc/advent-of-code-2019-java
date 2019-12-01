package io.hacksy.aoc.util;

import java.util.function.Supplier;

public class Performance {
    public static void timeAndPrint(Supplier<String> stringSupplier) {
        long startTime = System.nanoTime();
        String result = stringSupplier.get();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        System.out.println(String.format("Completed in %s seconds:", duration / 1_000_000_000.0));
        System.out.println(String.format(" - %s", result));
    }
}