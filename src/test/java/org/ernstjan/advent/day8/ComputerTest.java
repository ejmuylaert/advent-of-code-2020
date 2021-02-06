package org.ernstjan.advent.day8;

import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ComputerTest {

    @Test
    void firstSample() {
        String sample = "nop +0\n" +
                "acc +1\n" +
                "jmp +4\n" +
                "acc +3\n" +
                "jmp -3\n" +
                "acc -99\n" +
                "acc +1\n" +
                "jmp -4\n" +
                "acc +6\n";
        List<Instruction> program = ProgramLoader.load(CharStreams.fromString(sample));
        Computer computer = new Computer();

        assertThat(computer.run(program, new Terminate())).contains(5);
    }

    @Test
    void changeSample() {
        String sample = "nop +0\n" +
                "acc +1\n" +
                "jmp +4\n" +
                "acc +3\n" +
                "jmp -3\n" +
                "acc -99\n" +
                "acc +1\n" +
                "jmp -4\n" +
                "acc +6\n";
        List<Instruction> program = ProgramLoader.load(CharStreams.fromString(sample));
        assertThat(Debugger.fixProgram(program)).isEqualTo(8);
    }
}
