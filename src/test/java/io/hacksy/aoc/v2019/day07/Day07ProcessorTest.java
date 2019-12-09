package io.hacksy.aoc.v2019.day07;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Day07ProcessorTest {
    @Test
    void partOne01() {
        var p = new Day07Processor();
        var program = List.of(3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0).map(Long::valueOf);
        assertThat(p.partOne(program), is(43210L));
    }

    @Test
    void partOne02() {
        var p = new Day07Processor();
        var program = List.of(3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0).map(Long::valueOf);
        assertThat(p.partOne(program), is(54321L));
    }

    @Test
    void partOne03() {
        var p = new Day07Processor();
        var program = List.of(3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0).map(Long::valueOf);
        assertThat(p.partOne(program), is(65210L));
    }

    @Test
    void partTwo01() {
        var p = new Day07Processor();
        var program = List.of(3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5).map(Long::valueOf);
        assertThat(p.partTwo(program), is(139629729L));
    }

    @Test
    void partTwo02() {
        var p = new Day07Processor();
        var program = List.of(3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10).map(Long::valueOf);
        assertThat(p.partTwo(program), is(18216L));
    }
}