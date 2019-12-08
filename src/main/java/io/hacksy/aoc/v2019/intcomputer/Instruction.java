package io.hacksy.aoc.v2019.intcomputer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.hacksy.aoc.v2019.intcomputer.ImmutableState;

public interface Instruction {
    Logger logger = LoggerFactory.getLogger(Instruction.class.getName());

    class Add implements Instruction {
        static IntComputer.State execute(IntComputer.State state) {
            var p1 = Instruction.getParam(1, false, state);
            var p2 = Instruction.getParam(2, false, state);
            var p3 = Instruction.getParam(3, true, state);

            return ImmutableState.copyOf(state)
                    .withPointer(state.pointer() + 4)
                    .withMemory(state.memory().put(p3, p1 + p2));
        }
    }

    class Multiply implements Instruction {
        static IntComputer.State execute(IntComputer.State state) {
            var p1 = Instruction.getParam(1, false, state);
            var p2 = Instruction.getParam(2, false, state);
            var p3 = Instruction.getParam(3, true, state);

            return ImmutableState.copyOf(state)
                    .withPointer(state.pointer() + 4)
                    .withMemory(state.memory().put(p3, p1 * p2));
        }
    }

    class Input implements Instruction {
        static IntComputer.State execute(IntComputer.State state) {
            var p1 = Instruction.getParam(1, true, state);

            if (state.input().isEmpty()) {
                return ImmutableState.copyOf(state)
                        .withStatus(IntComputer.Status.PAUSED);
            }

            var dequeueTuple = state.input().dequeue();

            return ImmutableState.copyOf(state)
                    .withMemory(state.memory().put(p1, dequeueTuple._1()))
                    .withPointer(state.pointer() + 2)
                    .withInput(dequeueTuple._2());
        }
    }

    class Output implements Instruction {
        static IntComputer.State execute(IntComputer.State state) {
            var p1 = Instruction.getParam(1, false, state);

            return ImmutableState.copyOf(state)
                    .withPointer(state.pointer() + 2)
                    .withOutput(state.output().append(p1));
        }
    }

    class JumpIfTrue implements Instruction {
        static IntComputer.State execute(IntComputer.State state) {
            var p1 = Instruction.getParam(1, false, state);
            var p2 = Instruction.getParam(2, false, state);

            return ImmutableState.copyOf(state)
                    .withPointer(p1 != 0 ? p2 : state.pointer() + 3);
        }
    }

    class JumpIfFalse implements Instruction {
        static IntComputer.State execute(IntComputer.State state) {
            var p1 = Instruction.getParam(1, false, state);
            var p2 = Instruction.getParam(2, false, state);

            return ImmutableState.copyOf(state)
                    .withPointer(p1 == 0 ? p2 : state.pointer() + 3);
        }
    }

    class LessThan implements Instruction {
        static IntComputer.State execute(IntComputer.State state) {
            var p1 = Instruction.getParam(1, false, state);
            var p2 = Instruction.getParam(2, false, state);
            var p3 = Instruction.getParam(3, true, state);

            return ImmutableState.copyOf(state)
                    .withPointer(state.pointer() + 4)
                    .withMemory(state.memory().put(p3, p1 < p2 ? 1 : 0));
        }
    }

    class Equals implements Instruction {
        static IntComputer.State execute(IntComputer.State state) {
            var p1 = Instruction.getParam(1, false, state);
            var p2 = Instruction.getParam(2, false, state);
            var p3 = Instruction.getParam(3, true, state);

            return ImmutableState.copyOf(state)
                    .withPointer(state.pointer() + 4)
                    .withMemory(state.memory().put(p3, p1 == p2 ? 1 : 0));
        }
    }

    class Terminate implements Instruction {
        static IntComputer.State execute(IntComputer.State state) {
            return ImmutableState.copyOf(state).withStatus(IntComputer.Status.TERMINATED);
        }
    }

    private static int getParam(int index, boolean isWriteParam, IntComputer.State state) {
        var memory = state.memory();
        var pointer = state.pointer();
        var isPositional = !isWriteParam && Instruction.isPositional(memory.get(pointer).get(), index);

        return isPositional ? memory.get(memory.get(pointer + index).get()).get() : memory.get(pointer + index).get();
    }

    private static boolean isPositional(int opcode, int paramNumber) {
        String formatted = String.format("%05d", opcode);
        return formatted.charAt(3 - paramNumber) == '0';
    }

}
