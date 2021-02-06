package org.ernstjan.advent.day8;

class Accumulate implements Instruction {

    private final int parameter;

    public Accumulate(int parameter) {
        this.parameter = parameter;
    }

    @Override
    public boolean execute(Memory memory) {
        memory.increaseAccumulator(parameter);
        memory.advanceInstructionPointer(1);
        return true;
    }
}
