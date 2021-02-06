package org.ernstjan.advent.day8;

public interface Instruction {
    /**
     * Executes the instructions. Instructions may (will) change the memory.
     * <p>
     * When the program should be halted, false is returned.
     *
     * @param memory Memory of this 'compute'
     * @return whether the program should continue
     */
    boolean execute(Memory memory);
}
