package org.ernstjan.advent.day8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Computer {

    private final Memory memory = new Memory();

    public Optional<Integer> run(List<Instruction> program, Instruction replacement) {
        memory.loadProgram(program);

        boolean running = true;
        while (running) {
            Instruction instruction = memory.nextInstruction(replacement);
            running = instruction.execute(memory);
        }

        return memory.accumulator();
    }
}

class Memory {
    private int accumulator = 0;
    private boolean accumulatorIsValid = true;
    private int instructionPointer = 0;
    private final ArrayList<Instruction> instructions = new ArrayList<>();


    /**
     * Loads the program into memory and resets the accumulator & instruction pointer
     *
     * @param program list of instructions
     */
    public void loadProgram(List<Instruction> program) {
        instructions.clear();
        instructions.addAll(program);

        accumulator = 0;
        accumulatorIsValid = true;
        instructionPointer = 0;
    }

    Instruction nextInstruction(Instruction replacement) {
        Instruction instruction = instructions.get(instructionPointer);
        instructions.set(instructionPointer, replacement);
        return instruction;
    }

    Optional<Integer> accumulator() {
        if (accumulatorIsValid) {
            return Optional.of(accumulator);
        } else {
            return Optional.empty();
        }
    }

    public void increaseAccumulator(int delta) {
        this.accumulator += delta;
    }

    public void invalidateAccumulator() {
        accumulatorIsValid = false;
    }

    public void advanceInstructionPointer(int delta) {
        this.instructionPointer += delta;
    }
}

