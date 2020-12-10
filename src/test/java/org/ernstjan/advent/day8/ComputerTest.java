package org.ernstjan.advent.day8;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ComputerTest {

    @Test
    void sample() {
        List<String> lines = List.of(
                "nop +0",
                "acc +1",
                "jmp +4",
                "acc +3",
                "jmp -3",
                "acc -99",
                "acc +1",
                "jmp -4",
                "acc +6");

        Computer computer = new Computer(lines);
        boolean success = computer.run();
        assertFalse(success);
        assertEquals(computer.getAccumulator(), 5);
    }

    @Test
    void changeSample() {
        List<String> lines = List.of(
                "nop +0",
                "acc +1",
                "jmp +4",
                "acc +3",
                "jmp -3",
                "acc -99",
                "acc +1",
                "jmp -4",
                "acc +6");
        Debugger debugger = new Debugger(lines);
        assertEquals(debugger.fix(), 8);

    }
}
