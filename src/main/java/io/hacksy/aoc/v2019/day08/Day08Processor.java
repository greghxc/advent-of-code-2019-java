package io.hacksy.aoc.v2019.day08;

import io.hacksy.aoc.util.GifWriter;
import io.vavr.collection.List;
import io.vavr.collection.Map;

import static io.vavr.API.*;

public class Day08Processor {
    int partOne(int width, int height, String input) {
        var layers = getLayers(width, height, input).map(this::charCountMap);
        var layerWithLeastZeros = layers.minBy(l -> l.get("0").get().length());
        return layerWithLeastZeros.get().get("1").get().length() * layerWithLeastZeros.get().get("2").get().length();
    }

    void partTwo(int width, int height, String input, String fileName) {
        var layers = getLayers(width, height, input);
        var gifWriter = new GifWriter(width, height, 20);

        List.range(0, height).forEach(i ->
                List.range(0, width).forEach(j ->
                        List.range(0, layers.length()).reverse().forEach(k ->
                                Match(layers.get(k).charAt(i * width + j)).of(
                                    Case($('0'), () -> gifWriter.addPixel(j, i, false)),
                                    Case($('1'), () -> gifWriter.addPixel(j, i, true)),
                                    Case($(), () -> gifWriter)
                                )
                        )
                )
        );

        gifWriter.write(fileName);
    }

    List<String> getLayers(int width, int height, String input) {
        var intsPerLayer = width * height;
        var numberOfLayers = input.length() / intsPerLayer;
        return List.range(0, numberOfLayers)
                .map(i -> input.substring(i * intsPerLayer, i * intsPerLayer + intsPerLayer));
    }

    private Map<String, List<String>> charCountMap(String string) {
        var list = List.ofAll(string.toCharArray()).map(String::valueOf);
        return list.groupBy(c -> c);
    }
}
