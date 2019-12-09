package io.hacksy.aoc.v2019.intcomputer;

import io.vavr.Function1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.hacksy.aoc.v2019.intcomputer.ImmutableState;

import static io.vavr.API.*;

interface Instruction extends Function1<IntComputer.State, IntComputer.State> {
    Logger logger = LoggerFactory.getLogger(Instruction.class.getName());

    class Add implements Instruction {
        @Override
        public IntComputer.State apply(IntComputer.State state) {
            var p1 = Parameter.getParam(1, state);
            var p2 = Parameter.getParam(2, state);
            var p3 = Parameter.getParam(3, state);

            var newState = p3.write(p1.read() + p2.read());

            return ImmutableState.copyOf(newState)
                    .withPointer(state.pointer() + 4);
        }
    }

    class Multiply implements Instruction {
        @Override
        public IntComputer.State apply(IntComputer.State state) {
            var p1 = Parameter.getParam(1, state);
            var p2 = Parameter.getParam(2, state);
            var p3 = Parameter.getParam(3, state);

            var newState = p3.write(p1.read() * p2.read());

            return ImmutableState.copyOf(newState)
                    .withPointer(state.pointer() + 4);        }
    }

    class Input implements Instruction {
        @Override
        public IntComputer.State apply(IntComputer.State state) {
            var p1 = Parameter.getParam(1, state);

            if (state.input().isEmpty()) {
                return ImmutableState.copyOf(state)
                        .withStatus(IntComputer.Status.PAUSED);
            }

            var dequeueTuple = state.input().dequeue();

            var newState = p1.write(dequeueTuple._1());

            return ImmutableState.copyOf(newState)
                    .withPointer(state.pointer() + 2)
                    .withInput(dequeueTuple._2());        }
    }

    class Output implements Instruction {
        @Override
        public IntComputer.State apply(IntComputer.State state) {
            var p1 = Parameter.getParam(1, state);

            return ImmutableState.copyOf(state)
                    .withPointer(state.pointer() + 2)
                    .withOutput(state.output().append(p1.read()));        }
    }

    class JumpIfTrue implements Instruction {
        @Override
        public IntComputer.State apply(IntComputer.State state) {
            var p1 = Parameter.getParam(1, state);
            var p2 = Parameter.getParam(2, state);

            return ImmutableState.copyOf(state)
                    .withPointer(p1.read() != 0 ? p2.read() : state.pointer() + 3);
        }
    }

    class JumpIfFalse implements Instruction {
        @Override
        public IntComputer.State apply(IntComputer.State state) {
            var p1 = Parameter.getParam(1, state);
            var p2 = Parameter.getParam(2, state);

            return ImmutableState.copyOf(state)
                    .withPointer(p1.read() == 0 ? p2.read() : state.pointer() + 3);
        }
    }

    class LessThan implements Instruction {
        @Override
        public IntComputer.State apply(IntComputer.State state) {
            var p1 = Parameter.getParam(1, state);
            var p2 = Parameter.getParam(2, state);
            var p3 = Parameter.getParam(3, state);

            var newState = p3.write(p1.read() < p2.read() ? 1L : 0L);

            return ImmutableState.copyOf(newState)
                    .withPointer(state.pointer() + 4);
        }
    }

    class Equals implements Instruction {
        @Override
        public IntComputer.State apply(IntComputer.State state) {
            var p1 = Parameter.getParam(1, state);
            var p2 = Parameter.getParam(2, state);
            var p3 = Parameter.getParam(3, state);

            var newState = p3.write(p1.read() == p2.read() ? 1L : 0L);

            return ImmutableState.copyOf(newState)
                    .withPointer(state.pointer() + 4);
        }
    }

    class AdjustBase implements Instruction {
        @Override
        public IntComputer.State apply(IntComputer.State state) {
            var p1 = Parameter.getParam(1, state);

            return ImmutableState.copyOf(state)
                    .withPointer(state.pointer() + 2)
                    .withBase(state.base() + p1.read());
        }
    }

    class Terminate implements Instruction {
        @Override
        public IntComputer.State apply(IntComputer.State state) {
            return ImmutableState.copyOf(state).withStatus(IntComputer.Status.TERMINATED);
        }
    }

    static Function1<IntComputer.State, IntComputer.State> nextInstruction(IntComputer.State state) {
        String formatted = String.format("%02d", state.memory().get(state.pointer()).get());
        return Match(formatted.substring(formatted.length() - 2)).of(
                Case($("01"), new Instruction.Add()),
                Case($("02"), new Instruction.Multiply()),
                Case($("03"), new Instruction.Input()),
                Case($("04"), new Instruction.Output()),
                Case($("05"), new Instruction.JumpIfTrue()),
                Case($("06"), new Instruction.JumpIfFalse()),
                Case($("07"), new Instruction.LessThan()),
                Case($("08"), new Instruction.Equals()),
                Case($("09"), new Instruction.AdjustBase()),
                Case($("99"), new Instruction.Terminate())
        );
    }
}
