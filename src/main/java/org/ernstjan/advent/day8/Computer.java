package org.ernstjan.advent.day8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Computer {
    private final ArrayList<Instruction> program = new ArrayList<>();
    private int accumulator = 0;

    public Computer(List<String> lines) {
        lines.forEach(line -> {
                    Scanner lineScanner = new Scanner(line);
                    String op = lineScanner.next();
                    String value = lineScanner.next();

                    switch (op) {
                        case "nop":
                            program.add(new Instruction(Operation.NOOP, Integer.parseInt(value)));
                            break;
                        case "acc":
                            program.add(new Instruction(Operation.ACC, Integer.parseInt(value)));
                            break;
                        case "jmp":
                            program.add(new Instruction(Operation.JMP, Integer.parseInt(value)));
                            break;
                    }
                }
        );
    }

    public Computer(ArrayList<Instruction> listing) {
        program.addAll(listing);
    }

    public boolean run() {
        int lineNumber = 0;
        while (lineNumber < program.size()) {
            Instruction instruction = program.get(lineNumber);
            if (instruction.isExecuted()) {
                return false;
            }

            switch (instruction.getOp()) {
                case NOOP:
                    lineNumber++;
                    break;
                case JMP:
                    lineNumber += instruction.getValue();
                    break;
                case ACC:
                    accumulator += instruction.getValue();
                    lineNumber++;
                    break;
            }
            instruction.setExecuted(true);
        }

        return true;
    }

    public int getAccumulator() {
        return accumulator;
    }

    public ArrayList<Instruction> listing() {
        return program;
    }

    static class Instruction {
        private boolean executed = false;
        private final Operation op;
        private final int value;

        public Instruction(Operation op, int value) {
            this.op = op;
            this.value = value;
        }

        public void setExecuted(boolean executed) {
            this.executed = executed;
        }

        public boolean isExecuted() {
            return executed;
        }

        public Operation getOp() {
            return op;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Instruction{" +
                    "executed=" + executed +
                    ", op=" + op +
                    ", value=" + value +
                    '}';
        }
    }

    enum Operation {
        ACC,
        JMP,
        NOOP
    }
}
