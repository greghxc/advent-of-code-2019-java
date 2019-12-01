package io.hacksy.aoc.v2019.day00;

import io.hacksy.aoc.util.FileUtil;

import java.io.File;

class Day00Processor {
    int proccess(File file) {
        return FileUtil.fileToList(file)
                .sum().intValue();
    }
}
