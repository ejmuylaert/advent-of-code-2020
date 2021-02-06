package org.ernstjan.advent.day8;

public class Terminate implements Instruction {

    @Override
    public boolean execute(Memory memory) {
        return false;
    }
}
