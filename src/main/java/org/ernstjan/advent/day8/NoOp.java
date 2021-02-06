package org.ernstjan.advent.day8;

class NoOp implements Instruction {

    private final int parameter;

    public NoOp(int parameter) {
        this.parameter = parameter;
    }

    @Override
    public boolean execute(Memory memory) {
        memory.advanceInstructionPointer(1);
        return true;
    }

    public int getParameter() {
        return parameter;
    }
}
