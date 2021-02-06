package org.ernstjan.advent.day8;

public class Abort implements Instruction {

    @Override
    public boolean execute(Memory memory) {
        memory.invalidateAccumulator();
        return false;
    }
}
