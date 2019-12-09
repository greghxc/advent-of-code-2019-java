package io.hacksy.aoc.v2019.intcomputer;
import io.hacksy.aoc.v2019.intcomputer.ImmutableState;

public interface Parameter {
    long read();
    IntComputer.State write(long value);

    class Immediate implements Parameter {
        private final long immediateValue;

        public Immediate(long immediateValue) {
            this.immediateValue = immediateValue;
        }

        @Override
        public long read() {
            return immediateValue;
        }

        @Override
        public IntComputer.State write(long value) {
            throw new RuntimeException("Can't write to immediate parameter");
        }
    }

    class Positional implements Parameter {
        private final IntComputer.State state;
        private final long position;

        public Positional(IntComputer.State state, long position) {
            this.state = state;
            this.position = position;
        }

        @Override
        public long read() {
            return state.memory().get(position).getOrElse(0L);
        }

        @Override
        public IntComputer.State write(long value) {
            var mem = state.memory().put(position, value);
            return ImmutableState.copyOf(state)
                    .withMemory(mem);
        }
    }

    class Relative extends Positional {
        public Relative(IntComputer.State state, long position) {
            super(state, position + state.base());
        }
    }

    static Parameter getParam(int index, IntComputer.State state) {
        var memory = state.memory();
        var pointer = state.pointer();
        var opcode = String.format("%05d", memory.get(pointer).get());

        switch(opcode.charAt(3 - index)) {
            case '0':
                return new Parameter.Positional(state, memory.get(pointer + index).get());
            case '1':
                return new Parameter.Immediate(memory.get(pointer + index).get());
            case '2':
                return new Parameter.Relative(state, memory.get(pointer + index).get());
            default:
                throw new RuntimeException("Invalid mode id");
        }
    }
}
