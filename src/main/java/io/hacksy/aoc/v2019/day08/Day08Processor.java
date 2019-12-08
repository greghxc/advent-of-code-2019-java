package io.hacksy.aoc.v2019.day08;

import io.hacksy.aoc.util.GifWriter;
import io.vavr.collection.List;
import io.vavr.collection.Map;

import java.util.ArrayList;

public class Day08Processor {
    int partOne(int width, int height, String input) {
        var layers = getLayers(width, height, input).map(this::charCountMap);
        var layerWithLeastZeros = layers.minBy(l -> l.get("0").get().length());
        return layerWithLeastZeros.get().get("1").get().length() * layerWithLeastZeros.get().get("2").get().length();
    }

    void partTwo(int width, int height, String input, String fileName) {
        var layers = getLayers(width, height, input);
        var gifWriter = new GifWriter(width, height, 20);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = layers.length() - 1; k >= 0; k--) {
                    var pixel = layers.get(k).charAt(i * width + j);
                    if (pixel == '0') { gifWriter.addPixel(j, i, false); }
                    if (pixel == '1') { gifWriter.addPixel(j, i, true); }
                }
            }
        }
        gifWriter.write(fileName);
    }

    List<String> getLayers(int width, int height, String input) {
        var intsPerLayer = width * height;
        var numberOfLayers = input.length() / intsPerLayer;
        return List.range(0, numberOfLayers)
                .map(i -> input.substring(i * intsPerLayer, i * intsPerLayer + intsPerLayer));
    }

    private Map<String, List<String>> charCountMap(String string) {
        var charList = new ArrayList<String>();
        for (Character c : string.toCharArray()) {
            charList.add(String.valueOf(c));
        }
        var list = List.ofAll(charList);

        return list.groupBy(c -> c);
    }
}
