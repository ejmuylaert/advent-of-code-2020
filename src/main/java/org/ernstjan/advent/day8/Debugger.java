package org.ernstjan.advent.day8;

import java.util.List;
import java.util.Optional;

public class Debugger {

    static public int fixProgram(List<Instruction> program) {
        Computer computer = new Computer();
        program.add(new Terminate());

        for (int instructionNumber = 0; instructionNumber < program.size(); instructionNumber++) {
            Instruction instruction = program.get(instructionNumber);
            Instruction newInstruction = null;
            if (instruction instanceof NoOp) {
                newInstruction = new Jump(((NoOp) instruction).getParameter());
            } else if (instruction instanceof Jump) {
                newInstruction = new NoOp(0);
            }

            if (newInstruction == null) {
                continue;
            }

            program.set(instructionNumber, newInstruction);
            Optional<Integer> result = computer.run(program, new Abort());

            if (result.isPresent()) {
                return result.get();
            }

            program.set(instructionNumber, instruction);
        }

        throw new RuntimeException("Could not fix program....");
    }
}