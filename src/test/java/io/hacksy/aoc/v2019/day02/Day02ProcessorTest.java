package io.hacksy.aoc.v2019.day02;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day02ProcessorTest {

    @Test
    void partOne1() {
        var processor = new Day02Processor();
        assertThat(processor.partOne("1,0,0,0,99"), is("2"));
    }
    @Test
    void partOne2() {
        var processor = new Day02Processor();
        assertThat(processor.partOne("2,3,0,3,99"), is("2"));
    }
    @Test
    void partOne3() {
        var processor = new Day02Processor();
        assertThat(processor.partOne("2,4,4,5,99,0"), is("2"));
    }
    @Test
    void partOne4() {
        var processor = new Day02Processor();
        assertThat(processor.partOne("1,1,1,4,99,5,6,0,99"), is("30"));
    }
}