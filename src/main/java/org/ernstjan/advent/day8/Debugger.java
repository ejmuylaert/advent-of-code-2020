package org.ernstjan.advent.day8;

import java.util.ArrayList;
import java.util.List;

import static org.ernstjan.advent.day8.Computer.Operation.JMP;
import static org.ernstjan.advent.day8.Computer.Operation.NOOP;

public class Debugger {
    private final ArrayList<Computer.Instruction> program;

    public Debugger(List<String> lines) {
        program = new Computer(lines).listing();
    }

    public int fix() {
        for (int i = 0; i < program.size(); i++) {
            Computer.Instruction instruction = program.get(i);
            ArrayList<Computer.Instruction> copy = new ArrayList<>(program);
            // TODO: immutable solution?
            copy.forEach(in -> in.setExecuted(false));

            switch (instruction.getOp()) {
                case ACC:
                    break;
                case NOOP:
                    copy.set(i, new Computer.Instruction(JMP, instruction.getValue()));
                    break;
                case JMP:
                    copy.set(i, new Computer.Instruction(NOOP, instruction.getValue()));
                    break;
            }
            Computer computer = new Computer(copy);
            if (computer.run()) {
                return computer.getAccumulator();
            }
        }
        return 0;
    }
}
