package org.ernstjan.advent.day8;

class Jump implements Instruction {

    private final int parameter;

    public Jump(int parameter) {
        this.parameter = parameter;
    }

    @Override
    public boolean execute(Memory memory) {
        memory.advanceInstructionPointer(parameter);
        return true;
    }

    public int getParameter() {
        return parameter;
    }
}
