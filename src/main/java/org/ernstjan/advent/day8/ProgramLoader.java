package org.ernstjan.advent.day8;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.ArrayList;
import java.util.List;

public class ProgramLoader {

    public static List<Instruction> load(CharStream stream) {
        ProgramLexer lexer = new ProgramLexer(stream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        ProgramParser parser = new ProgramParser(tokenStream);
        ProgramParser.ProgramContext tree = parser.program();
        LoadProgramVisitor programLoader = new LoadProgramVisitor();

        return programLoader.visit(tree);
    }
}

class LoadProgramVisitor extends ProgramBaseVisitor<List<Instruction>> {

    private final List<Instruction> program = new ArrayList<>();

    @Override
    public List<Instruction> visitNoOp(ProgramParser.NoOpContext ctx) {
        int parameter = Integer.parseInt(ctx.NUMBER().getText());
        program.add(new NoOp(parameter));

        return program;
    }

    @Override
    public List<Instruction> visitJump(ProgramParser.JumpContext ctx) {
        int parameter = Integer.parseInt(ctx.NUMBER().getText());
        program.add(new Jump(parameter));

        return program;
    }

    @Override
    public List<Instruction> visitAcc(ProgramParser.AccContext ctx) {
        int parameter = Integer.parseInt(ctx.NUMBER().getText());
        program.add(new Accumulate(parameter));

        return program;
    }

    @Override
    protected List<Instruction> defaultResult() {
        return program;
    }
}
