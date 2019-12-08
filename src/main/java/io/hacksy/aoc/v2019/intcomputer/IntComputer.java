package io.hacksy.aoc.v2019.intcomputer;

import io.vavr.API;
import io.vavr.Function0;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Queue;
import org.immutables.value.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.hacksy.aoc.v2019.intcomputer.ImmutableState;

import static io.vavr.API.*;

public class IntComputer {
    private static Logger logger = LoggerFactory.getLogger(IntComputer.class.getName());

    private State state;

    public IntComputer(List<Integer> program, List<Integer> inputList) {
        this.state = ImmutableState.builder()
                .memory(program.zipWithIndex().toMap(Tuple2::_2, Tuple2::_1))
                .input(Queue.ofAll(inputList))
                .output(List.empty())
                .pointer(0)
                .status(Status.OK)
                .build();
    }

    public List<Integer> run() {
        state = ImmutableState.copyOf(state).withStatus(Status.OK);
        while (state.status() == Status.OK) { state = getInstruction().get(); }
        return state.output();
    }

    public IntComputer addInput(int input) {
        state = ImmutableState.copyOf(state).withInput(state.input().append(input));
        return this;
    }

    public List<Integer> dumpMemory() {
        var maxKey = state.memory().keySet().max().get();
        return List.range(0, maxKey + 1)
                .map(k -> state.memory().get(k).getOrNull());
    }

    public Status getStatus() {
        return state.status();
    }

    private Function0<State> getInstruction() {
        String formatted = String.format("%02d", state.memory().get(state.pointer()).get());
        return () -> Match(formatted.substring(formatted.length() - 2)).of(
                API.Case($("01"), () -> Instruction.Add.execute(state)),
                Case($("02"), () -> Instruction.Multiply.execute(state)),
                Case($("03"), () -> Instruction.Input.execute(state)),
                Case($("04"), () -> Instruction.Output.execute(state)),
                Case($("05"), () -> Instruction.JumpIfTrue.execute(state)),
                Case($("06"), () -> Instruction.JumpIfFalse.execute(state)),
                Case($("07"), () -> Instruction.LessThan.execute(state)),
                Case($("08"), () -> Instruction.Equals.execute(state)),
                Case($("99"), () -> Instruction.Terminate.execute(state))
        );
    }

    @Value.Immutable
    public interface State {
        Map<Integer, Integer> memory();
        Queue<Integer> input();
        List<Integer> output();
        Integer pointer();
        Status status();
    }

    public enum Status {
        OK, PAUSED, TERMINATED
    }
}
