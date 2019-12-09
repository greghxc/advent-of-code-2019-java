package io.hacksy.aoc.v2019.intcomputer;

import io.vavr.API;
import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Queue;
import org.immutables.value.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.hacksy.aoc.v2019.intcomputer.ImmutableState;

public class IntComputer {
    private static Logger logger = LoggerFactory.getLogger(IntComputer.class.getName());

    private State state;

    public IntComputer(List<Long> program, List<Long> inputList) {
        this.state = ImmutableState.builder()
                .memory(program.zipWithIndex().toMap(t -> Long.valueOf(t._2()), Tuple2::_1))
                .input(Queue.ofAll(inputList))
                .output(List.empty())
                .pointer(0L)
                .status(Status.OK)
                .base(0L)
                .programLength((long) program.length())
                .build();
    }

    public List<Long> run() {
        state = ImmutableState.copyOf(state).withStatus(Status.OK);
        while (state.status() == Status.OK) {
            state = Instruction.nextInstruction(state).apply(state);
        }
        return state.output();
    }

    public IntComputer addInput(long input) {
        state = ImmutableState.copyOf(state).withInput(state.input().append(input));
        return this;
    }

    public List<Long> dumpMemory() {
        var maxKey = state.memory().keySet().max().get();
        return List.range(0, maxKey + 1)
                .map(k -> state.memory().get(k).getOrNull());
    }

    public Status getStatus() {
        return state.status();
    }

    public State getState() { return state; }

    @Value.Immutable
    public interface State {
        Map<Long, Long> memory();
        Queue<Long> input();
        List<Long> output();
        Long pointer();
        Status status();
        Long base();
        Long programLength();
    }

    public enum Status {
        OK, PAUSED, TERMINATED
    }
}
